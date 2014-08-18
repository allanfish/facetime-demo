package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.FreemarkerHelper;

/**
 * 
 * 测试在freemarker中List的使用. Set和数组与List的用法是一样的.
 * @author yufei
 *
 */
public class ListTest {

	public static void main(String[] args) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<User> list = new ArrayList<User>();
		list.add(new User("name1", "pwd1"));
		for (int i = 2; i < 5; i++)
			list.add(new User("name" + i, "pwd" + i, i));
		dataMap.put("userList", list);
		//		FreemarkerHelper.create(ListTest.class, "ListTest.ftl", "temp.html", dataMap);
		String destContent = FreemarkerHelper.contentAfterCreate(ListTest.class, "ListTest.ftl", "ListTest.html",
				dataMap);
		System.out.println(destContent);
	}

}
