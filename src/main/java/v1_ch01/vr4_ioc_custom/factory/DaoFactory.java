package v1_ch01.vr4_ioc_custom.factory;

import v1_ch01.vr3_composition.conn_maker.DConnectionMaker;
import v1_ch01.vr3_composition.dao.UserDao;

public class DaoFactory {
	public UserDao userDao() {
		return new UserDao(new DConnectionMaker());
	}
}
