package v1_ch01.vr2_inheritance.dao;

import v1_ch01.util.conn.Connection;
import v1_ch01.util.conn.DConnection;

public class DUserDao extends UserDao {
	@Override
	public Connection getConnection() {
		return new DConnection().getConnection();
	}
}
