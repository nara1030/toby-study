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
}
