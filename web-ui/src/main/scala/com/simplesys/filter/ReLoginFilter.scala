package com.simplesys.filter

import javax.servlet.annotation.WebFilter

import com.simplesys.akka.http.LoginedData1
import com.simplesys.akka.http.filter.AkkaPartialFilter
import com.simplesys.app._
import com.simplesys.common.Strings._
import com.simplesys.common.equality.SimpleEquality._
import com.simplesys.isc.dataBinging.DSRequestDyn._
import com.simplesys.jdbc.control.classBO.Where
import com.simplesys.jdbc.exception.NoDataFoundException
import com.simplesys.messages.ActorConfig._
import com.simplesys.messages.MessageExt
import com.simplesys.servlet.{FilterChain, ServletRequest, ServletResponse}
import com.simplesys.tuple.{TupleSS2, TupleSS6}
import ru.simplesys.defs.bo.admin._

import scalaz.{Failure, Success}

//@WebFilter(urlPatterns = Array("/logic/*"), asyncSupported = true)
class ReLoginFilter extends AkkaPartialFilter {

    override protected def DoFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val req = request.toHttp
        val resp = response.toHttp

        val session = req.Session
        val sessionContext = new SessionContext(session)

        def getAuth: Unit = {
            getAutentification match {
                case LoginedData1(str, login, id, captionUser, codeGroup) if str == "" =>
                    SuccesAuthentication(req, resp, login, id, captionUser, codeGroup)
                case LoginedData1(str, _, _, _, _) if str != "" =>
                    FailureAuthentication(req, resp, str)
            }
        }

        getKindOperation match {
            case "login" =>
                getAuth
            case "logout" =>
                chain.DoFilter(request, response)
            case anyOperation =>
                logger trace s"!!!!! Request URI: $anyOperation"

                if (!sessionContext.sessionIsValidate) {
                    LoginRequiredResponse(req, resp) //Работает только как заглушка 'транспорта' по requestу
                    SendMessage(MessageExt(channels = LoginRequiredChannel))
                }
                else
                    chain.DoFilter(request, response)
        }

        def getKindOperation: String = {
            val res = req.RequestURI.replace(req.ContextPath, strEmpty) match {
                case "logic/login" => "login"
                case "logic/logout" => "logout"
                case _ => req.RequestURI
            }
            logger.trace(s"kindOperation: ${res}")
            res
        }

        def getAutentification: LoginedData1 = {

            val requestData = req.toDSRequest
            val login = requestData.getString("login")
            val password = requestData.getString("password")

            logger trace (s"login: ${login}")
            logger trace (s"pasword: ${password}")

            val user = UserBo(sessionContext.getDS)
            val userGroup = UserGroupBo(sessionContext.getDS)

            logger debug "---------------------------------------------------------------------------------------------------------------------------------------"
            logger debug s"login: $login, password: $password"

            user.selectOne(user.di ~ user.login ~ user.caption ~ user.group ~ user.login ~ user.password, where = Where(user.login === login) And (user.password === password)) result match {
                case Success(TupleSS6(userID, loginedUser, captionUser, code, _, _)) =>
                    logger debug s"userID $userID, loginedUser: $loginedUser, captionUser: $captionUser, codeGroup: $code"
                    logger debug "--------------------------------------------------------------------------------------------------------------------------------------"


                    for (_session <- session) {
                        _session.Attribute("userId", Some(userID))
                        _session.Attribute("loginedUser", Some(loginedUser))
                        _session.Attribute("captionUser", Some(captionUser))
                        _session.Attribute("codeGroup", Some(code))
                        _session.Attribute("logged", Some(true))
                    }

                    code.headOption match {
                        case None ⇒
                            LoginedData1(strEmpty, login, userID, captionUser, "Unknown")
                        case Some(group) ⇒
                            userGroup.selectOne(userGroup.di ~ userGroup.codeGroup, where = Where(userGroup.di === group)) result match {
                                case Success(TupleSS2(di, codeGroup)) ⇒
                                    LoginedData1(strEmpty, login, userID, captionUser, codeGroup)
                                case Failure(e) => e match {
                                    case e: NoDataFoundException =>
                                        LoginedData1(e.getMessage)
                                }
                            }
                    }

                case Failure(e) => e match {
                    case e: NoDataFoundException =>

                        logger debug s"NoDataFoundException"
                        logger debug "--------------------------------------------------------------------------------------------------------------------------------------"

                        session match {
                            case Some(_session) =>
                                _session RemoveAttribute "userId"
                                _session RemoveAttribute "loginedUser"
                                _session RemoveAttribute "captionUser"
                                _session RemoveAttribute "codeGroup"
                                _session RemoveAttribute "logged"

                                if (login === "root") {
                                    user.insertP(User(di = 0L, login = "root", firstName = None, secondName = None, lastName = login, password = password, active = true, group = None)) result match {
                                        case Success(_) =>
                                            for (_session <- session) {
                                                _session.Attribute("userId", Some(0))
                                                _session.Attribute("loginedUser", Some("root"))
                                                _session.Attribute("captionUser", Some("root"))
                                                _session.Attribute("codeGroup", Some(strEmpty))
                                                _session.Attribute("logged", Some(true))
                                            }
                                            LoginedData1(strEmpty, "root")
                                        case Failure(e) =>
                                            LoginedData1("Аутентификация не прошла :-(")
                                    }
                                } else
                                    user.selectOne(user.di ~ user.caption ~ user.login, where = Where(user.login === "root")) result match {
                                        case Success(_) =>
                                            LoginedData1("Аутентификация не прошла :-(")
                                        case Failure(e) => e match {
                                            case e: NoDataFoundException =>
                                                LoginedData1("Вам предоставляется право ввести ROOT пользователя. Введите обязательный логин root и пароль по Вашему усмотрению")
                                        }
                                    }
                            case None =>
                                LoginedData1("Not Session exists")
                        }
                    case e: Throwable =>
                        LoginedData1(e.getMessage)
                }
            }
        }
    }
}
