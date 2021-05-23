package v1_ch01.util.db;

import java.util.HashMap;
import java.util.Map;

import v1_ch01.util.conn.Connection;
import v1_ch01.util.vo.User;

public class CustomDb {
	private static Map<String, User> users = new HashMap<String, User>();
	
	public void insert(Connection conn, User user) {
		if (conn == null) {
			System.out.println("DB 입력 실패");
			return;
		}
		
		users.put(user.getId(), user);
	}
	
	public User get(Connection conn, String id) {
		if (conn == null) {
			System.out.println("DB 조회 실패");
			return null;
		}
		
		return users.get(id);
	}
}
