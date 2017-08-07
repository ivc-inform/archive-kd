package com.simplesys.app

import com.simplesys.common.Strings._
import com.simplesys.log.Logging
import com.simplesys.oracle.pool.OraclePoolDataSource
import com.simplesys.servlet.http.HttpSession
import com.simplesys.servlet.{ServletActor, ServletContext}
import com.simplesys.sql.SQLDialect
import oracle.jdbc.OracleConnection

trait SessionContextSupport {
    this: ServletActor =>

    lazy val sessionContext = new SessionContext(request.Session)

    def getLoginedUser = sessionContext.getLoginedUser
    def getCaptionUser = sessionContext.getCaptionUser
    def getUserId = sessionContext.getUserId

    implicit lazy val oraclePool:OraclePoolDataSource = sessionContext.getOraclePool //Не убирать !!!
}

class SessionContext(protected val session: Option[HttpSession]) extends Logging {

    private[this] var logged = false
    def getLoged = logged

    private[this] var userId: BigDecimal = 0.0
    def getUserId = userId

    private[this] var loginedUser = strEmpty
    def getLoginedUser = loginedUser

    private[this] var captionUser = strEmpty
    def getCaptionUser = captionUser

    private[this] var oraclePool: OraclePoolDataSource = null
    def getOraclePool = oraclePool

    def getSQLDialect: SQLDialect = oraclePool.sqlDialect

    /*private[this] var birtEngine: IReportEngine = null
    def getBirtEngine = birtEngine*/

    private[this] var servletContext: ServletContext = null
    //def getServletContext = servletContext

    for (_session <- session) {
        _session.LogSession
        servletContext = _session.ServletContext

        logged = _session.Attribute(s"logged") match {
            case Some(value: Boolean) => value
            case _ => false
        }

        userId = _session.Attribute(s"userId") match {
            case Some(value: BigDecimal) => value
            case _ => 0.0
        }

        loginedUser = _session.Attribute(s"loginedUser") match {
            case Some(str: String) => str
            case _ => strEmpty
        }

        captionUser = _session.Attribute(s"captionUser") match {
            case Some(str: String) => str
            case _ => strEmpty
        }

        oraclePool = servletContext.Attribute(s"ds") match {
            case Some(value: OraclePoolDataSource) => value
            case _ => throw new RuntimeException(s"Нет DS")
        }
    }

    def Invalidate() {
        for (_session <- session)
            _session.Invalidate()
    }

    def sessionIsValidate: Boolean = {
        val res = getLoginedUser != strEmpty
        logger.trace(s"SessionIsValidate: ${res}")
        res
    }
}
