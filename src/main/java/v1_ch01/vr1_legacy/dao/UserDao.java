package v1_ch01.vr1_legacy.dao;

import v1_ch01.util.conn.Connection;
import v1_ch01.util.conn.DConnection;
import v1_ch01.util.db.CustomDb;
import v1_ch01.util.exception.FailToGetUserByIdException;
import v1_ch01.util.vo.User;

public class UserDao {
	public void add(User user) {
		// 1. 커넥션 획득
		Connection conn = new DConnection().getConnection();
		
		// 2. 입력
		CustomDb db = new CustomDb();
		db.insert(conn, user);
	}
	
	public User get(String id) {
		// 1. 커넥션 획득
		Connection conn = new DConnection().getConnection();
		
		// 2. 조회
		CustomDb db = new CustomDb();
		User user = db.get(conn, id);
		
		return user;
	}
	
	// test용 main 메소드
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("test1");
		user.setName("테스트");
		user.setPassword("tt");
		
		// 1. 등록
		dao.add(user);
		
		System.out.println(user.getId() + " 등록 성공");
		
		// 2. 조회
		System.out.println(dao.get(user.getId()).getId() + " 조회 성공");
	}
}
