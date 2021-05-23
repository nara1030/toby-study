package v1_ch01.vr3_composition.conn_maker;

import v1_ch01.util.conn.Connection;
import v1_ch01.util.conn.DConnection;

public class DConnectionMaker implements ConnectionMaker {
	public DConnectionMaker() {
		System.out.println("DConnectionMaker 생성");
	}
	
	public Connection openConnection() {
		return new DConnection().getConnection();
	}
}
