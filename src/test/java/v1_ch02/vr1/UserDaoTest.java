package v1_ch02.vr1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import v1_ch01.util.vo.User;
import v1_ch01.vr3_composition.dao.UserDao;
import v1_ch01.vr5_ioc_spring.factory.DaoFactory;

public class UserDaoTest {
	/**
	 * junit을 통한 테스트 검증 자동화
	 */
	@Test
	public void addAndGet() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("test1");
		user.setName("테스트");
		user.setPassword("tt");
		User user2 = new User();
		user2.setId("test1");
		user2.setName("테스트2");
		user2.setPassword("tt2");
		
		// 1. 등록
		dao.add(user);
		// 2. 조회
		User userInserted = dao.get(user.getId());
		
		assertThat(userInserted.getName(), is(user.getName()));
		assertThat(userInserted.getPassword(), is(user.getPassword()));
		
		// 1. 등록
		dao.add(user2);
		// 2. 조회
		User userInserted2 = dao.get(user2.getId());
		
//		assertThat(userInserted2.getName(), is(user2.getName()));
//		assertThat(userInserted2.getPassword(), is(user2.getPassword()));
	}
}
