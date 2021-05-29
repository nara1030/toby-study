package v2_ch02.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import v2_ch02.conn_maker.ConnectionMaker;
import v2_ch02.conn_maker.DConnectionMaker;
import v2_ch02.dao.UserDao;



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
