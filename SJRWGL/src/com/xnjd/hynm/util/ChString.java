package com.xnjd.hynm.util;

public class ChString {

	/**
	 * 解决中文乱码的输出问题
	 * @param str
	 * @return
	 */
	public static String chStr(String str) {
		if (str == null) {
			str = "";
		} else {
			try {
				str = (new String(str.getBytes("iso-8859-1"), "UTF-8")).trim();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return str;
	}
}
