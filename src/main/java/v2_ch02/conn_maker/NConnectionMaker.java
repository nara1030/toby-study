package v2_ch02.conn_maker;

import v2_ch02.conn.Connection;
import v2_ch02.conn.NConnection;

public class NConnectionMaker implements ConnectionMaker {
	public NConnectionMaker() {
		System.out.println("NConnectionMaker 생성");
	}
	
	public Connection openConnection() {
		return new NConnection().getConnection();
	}
}
