package com.simplesys.js.com.simplesys.SmartClient.App

object LoggedGroup {
    var logged = false
    var codeGroup: Option[String] = None
    def isDevsGroup(): Boolean = codeGroup.getOrElse(false) == "developers"
    def isAdminsGroup(): Boolean = codeGroup.getOrElse(false) == "admins"
}

