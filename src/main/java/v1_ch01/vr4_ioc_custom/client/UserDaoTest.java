package v1_ch01.vr4_ioc_custom.client;

import v1_ch01.util.vo.User;
import v1_ch01.vr3_composition.dao.UserDao;
import v1_ch01.vr4_ioc_custom.factory.DaoFactory;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao dao = new DaoFactory().userDao();
		// 오브젝트 동일성 테스트
		UserDao dao2 = new DaoFactory().userDao();
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
