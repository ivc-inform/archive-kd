package com.simplesys.filter

import javax.servlet.annotation.WebFilter

@WebFilter(urlPatterns = Array("/logic/*"), asyncSupported = true)
class ReLoginFilter extends ReLoginBaseFilter
