package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtils {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		System.out.println(verifyCodeExpected);
		String verifyCodeActual = HttpServletRequestUitl.getString(request, "verifyCodeActual");
		System.out.println(verifyCodeActual);
		if (verifyCodeActual == null || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
			return false;
		}
		return true;

	}

}
