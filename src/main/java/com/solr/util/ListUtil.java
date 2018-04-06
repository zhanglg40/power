package com.solr.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ListUtil {
	
	/**添加并去重
	 * @param list
	 * @param str
	 * @return
	 */
	public static List<String> listAddOnly(List<String> list , String str ) {
		Collection<String> set=new HashSet<String>();
		set.addAll(list);//给set填充
		set.add(str);
		list.clear();//清空list
		list.addAll(set);
		set.clear();
		return list;
	}

}
