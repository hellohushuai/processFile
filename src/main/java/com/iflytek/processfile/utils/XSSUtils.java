package com.iflytek.processfile.utils;


public class XSSUtils {
	/*去除字符串中html标签*/
	public static String stripHtml(String content) {
		if (content != null) {
			// <p>段落替换为换行
			content = content.replaceAll("<p .*?>", "\r\n");
			// <br><br/>替换为换行
			content = content.replaceAll("<br\\s*/?>", "\r\n");
			// 去掉其它的<>之间的东西
			content = content.replaceAll("\\<.*?>", "");
			// 去掉空格
			content = content.replaceAll(" ", "");
			return content;
		}
		return null;
	}
}
