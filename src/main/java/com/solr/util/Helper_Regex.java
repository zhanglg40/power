package com.solr.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper_Regex {
	public static String matcher_return_string(String regex, CharSequence input) {
		if (regex == null)
			return "";
		if (input == null)
			return "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group(1).trim();
		}
		return "";
	}

	/**
	 * @param regex 匹配规则
	 * @param input 输入的全字符串
	 * @param i 想匹配几个
	 * @return 数组第一个是匹配的全字符串，第二个是你想要的第一个匹配字符串，第二个是你想要的第二个匹配的字符串，。。。。。
	 */
	public static String[] matcher_return_string1(String regex, CharSequence input, int i) {
		if (regex == null)
			return null;
		if (input == null)
			return null;
		String[] a = new String[i + 1];
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			for (int j = 1; j <= i; j++) {
				a[j] = matcher.group(j);
			}

		}
		return a;
	}

	public static List<String> matcher_return_list(String regex, CharSequence input) {
		List<String> list = new ArrayList<String>();
		if (regex == null)
			return list;
		if (input == null)
			return list;

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			list.add(matcher.group(1).trim());
			// System.out.println(matcher.group(1));
		}
		return list;
	}

	public static List<String> matcher_return_list1(String regex, CharSequence input) {
		List<String> list = new ArrayList<String>();
		if (regex == null)
			return list;
		if (input == null)
			return list;

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			list.add(matcher.group(1).trim());
			list.add(matcher.group(2).trim());
			list.add(matcher.group(3).trim());
			list.add(matcher.group(4).trim());
		}
		return list;
	}

}
