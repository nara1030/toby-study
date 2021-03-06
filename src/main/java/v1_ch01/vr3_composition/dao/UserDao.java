package v1_ch01.vr3_composition.dao;

import v1_ch01.util.conn.Connection;
import v1_ch01.util.db.CustomDb;
import v1_ch01.util.vo.User;
import v1_ch01.vr3_composition.conn_maker.ConnectionMaker;

public class UserDao {
	private ConnectionMaker connectionMaker;

	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public void add(User user) {
		// 1. 커넥션 획득
		Connection conn = connectionMaker.openConnection();

		// 2. 입력
		CustomDb db = new CustomDb();
		db.insert(conn, user);
	}
	
	public User get(String id) {
		// 1. 커넥션 획득
		Connection conn = connectionMaker.openConnection();

		// 2. 조회
		CustomDb db = new CustomDb();
		User user = db.get(conn, id);

		return user;
	}
	
	// v1_ch02. 테스트 결과의 일관성
	public void deleteAll() {
		// 1. 커넥션 획득
		Connection conn = connectionMaker.openConnection();
		
		// 2. 삭제
		CustomDb db = new CustomDb();
		db.deleteAll(conn);
	}
	
	public Integer getCount() {
		// 1. 커넥션 획득
		Connection conn = connectionMaker.openConnection();
		
		// 2. 카운트
		CustomDb db = new CustomDb();
		return db.getCount(conn);
	}
}
