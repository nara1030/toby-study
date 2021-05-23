package v1_ch01.vr3_composition.conn_maker;

import v1_ch01.util.conn.Connection;
import v1_ch01.util.conn.NConnection;

public class NConnectionMaker implements ConnectionMaker {
	public NConnectionMaker() {
		System.out.println("NConnectionMaker 생성");
	}
	
	public Connection openConnection() {
		return new NConnection().getConnection();
	}
}
