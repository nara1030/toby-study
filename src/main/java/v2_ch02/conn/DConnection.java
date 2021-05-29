package v2_ch02.conn;

public class DConnection implements Connection {
	public Connection getConnection() {
		System.out.println("DConnection 생성");
		return new DConnection();
	}
}
