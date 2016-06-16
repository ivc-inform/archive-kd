package com.simplesys.SmartClient.App

object LoggedGroup {
    var logged = false
    var codeGroup: Option[String] = None
    def isDevsGroup(): Boolean = codeGroup.getOrElse("") == "developers"
    def isAdminsGroup(): Boolean = codeGroup.getOrElse("") == "admins"
    def isRoot(): Boolean = codeGroup.getOrElse("") == ""
}

