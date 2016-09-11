package com.github.tyru.jaxrshelloapp.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {

	public static String toLoggingMsg(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}
}
