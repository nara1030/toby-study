package v2_ch02.conn;

public class NConnection implements Connection {
	public Connection getConnection() {
		System.out.println("NConnection 생성");
		return new NConnection();
	}
}
