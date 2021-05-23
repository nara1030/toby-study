package v1_ch01.vr5_ioc_spring.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import v1_ch01.vr3_composition.conn_maker.ConnectionMaker;
import v1_ch01.vr3_composition.conn_maker.DConnectionMaker;
import v1_ch01.vr3_composition.dao.UserDao;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
