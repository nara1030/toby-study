package v1_ch01.util.conn;

import v1_ch01.util.conn.Connection;

public class NConnection implements Connection {
	public Connection getConnection() {
		System.out.println("NConnection 생성");
		return new NConnection();
	}
}
