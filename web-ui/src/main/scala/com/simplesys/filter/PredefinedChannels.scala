package com.simplesys.filter

import com.simplesys.servlet.http.sse.SseChannel

case object LogoutRequiredChannel extends SseChannel {
    override def serrialize: String = "LogoutRequired"
}

case object LoginRequiredChannel extends SseChannel {
    override def serrialize: String = "LoginRequired"
}
