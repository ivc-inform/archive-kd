package com.simplesys.filter

import javax.servlet.annotation.WebFilter

import com.simplesys.SmartClient.App.filter.ReLoginBaseFilter

@WebFilter(urlPatterns = Array("/logic/*"), asyncSupported = true)
class ReLoginFilter extends ReLoginBaseFilter
