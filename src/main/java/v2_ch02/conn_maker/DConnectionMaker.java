package v2_ch02.conn_maker;

import v2_ch02.conn.Connection;
import v2_ch02.conn.DConnection;

public class DConnectionMaker implements ConnectionMaker {
	public DConnectionMaker() {
		System.out.println("DConnectionMaker 생성");
	}
	
	public Connection openConnection() {
		return new DConnection().getConnection();
	}
}
