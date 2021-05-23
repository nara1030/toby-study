package v1_ch01.util.conn;

import v1_ch01.util.conn.Connection;

public class DConnection implements Connection {
	public Connection getConnection() {
		System.out.println("DConnection 생성");
		return new DConnection();
	}
}
