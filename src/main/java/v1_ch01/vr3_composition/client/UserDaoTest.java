package v1_ch01.vr3_composition.client;

import v1_ch01.util.vo.User;
import v1_ch01.vr3_composition.conn_maker.ConnectionMaker;
import v1_ch01.vr3_composition.conn_maker.NConnectionMaker;
import v1_ch01.vr3_composition.dao.UserDao;

public class UserDaoTest {
	public static void main(String[] args) {
		ConnectionMaker connectionMaker = new NConnectionMaker();
		UserDao dao = new UserDao(connectionMaker);
		
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
