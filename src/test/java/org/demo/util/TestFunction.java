package org.demo.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.demo.utils.ArrayUtil;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class TestFunction {

	@Test
	public void testBinarySearch() {
		String[] paths = new String[] {"/login.jsp","/register.jsp"}; // 白名单
		boolean bool = !(Arrays.binarySearch(paths, "/register.jsp") < 0);
		assertEquals(true,bool);
	}
	
	@Test
	public void testSplit() {
		String str = "js,jsp";
		String[] exts = str.split(",");
		boolean bool = Arrays.binarySearch(exts, "js") >= 0;
		bool = ArrayUtil.eleExists(exts, "jsp");
		assertEquals(true,bool);
	}
	
	@Test
	public void testBeanUtils() {
		class User {
			public User() {};
			private String name;
			public void setName(String name) {
				this.name = name;
			}
			public String getName() {
				return this.name;
			}
		}
		User u = new User();
		u.setName("hello");
		System.out.println(u.getName());
		User i = new User();
		BeanUtils.copyProperties(u, i);
		System.out.println(i.getName());
	}
}
