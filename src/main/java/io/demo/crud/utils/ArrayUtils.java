package io.demo.crud.utils;

public class ArrayUtils {
	/**
	 * 判断数组中是否有该元素
	 * @param str
	 * @param ele
	 * @return
	 */
	public static boolean eleExists(String[] str, String ele) {
		boolean flag = false;
		for(int i = 0; i < str.length; i++) {
			if(str[i].equals(ele)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}

