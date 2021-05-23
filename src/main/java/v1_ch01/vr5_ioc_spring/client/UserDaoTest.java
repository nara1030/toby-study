package v1_ch01.vr5_ioc_spring.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import v1_ch01.util.vo.User;
import v1_ch01.vr3_composition.dao.UserDao;
import v1_ch01.vr5_ioc_spring.factory.DaoFactory;

public class UserDaoTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		// 오브젝트 동일성 테스트
		UserDao dao2 = applicationContext.getBean("userDao", UserDao.class);
		System.out.println(dao);
		System.out.println(dao2);

		User user = new User();
		user.setId("test1");
		user.setName("테스트");
		user.setPassword("tt");

		// 1. 등록
		dao.add(user);
		System.out.println(user.getId() + " 등록 성공");

		// 2. 조회
		System.out.println(dao.get(user.getId()).getId() + " 조회 성공");
		
	}
}
