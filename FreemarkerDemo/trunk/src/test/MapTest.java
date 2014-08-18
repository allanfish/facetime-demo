package test;

import java.util.HashMap;
import java.util.Map;

import commons.FreemarkerHelper;

/**
 * 测试在freemarker中如何使用Map
 * 
 * @author yufei
 *
 */
public class MapTest {

	public static void main(String[] args) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, User> userMap = new HashMap<String, User>();
		userMap.put("user1", new User("yufei1", "pwd1"));
		userMap.put("user2", new User("yufei2", "pwd2"));
		userMap.put("user3", new User("yufei3", "pwd3"));
		data.put("userMap", userMap);
		data.put("keySet", userMap.keySet());

		//		FreemarkerHelper.create(MapTest.class, "MapTest.ftl", "MapTest.html", data);
		String dest = FreemarkerHelper.contentAfterCreate(MapTest.class, "MapTest.ftl", "temp.html", data);
		System.out.println(dest);
	}
}
