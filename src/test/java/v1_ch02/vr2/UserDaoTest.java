package v1_ch02.vr2;

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
	 * 단위 테스트: 동일한 결과를 보장하는 테스트
	 *           즉, 코드가 바뀌지 않는다면 매번 실행할 때마다 동일한 테스트 결과를 얻을 수 있어야 함
	 *           (DB에 남아있는 데이터와 같은 외부 환경에 영향이나 테스트 실행 순서에 영향을 받아선 안 됨)
	 * 
	 * 동일한 테스트 결과를 얻을 수 있는 방법
	 *  1. addAndGet() 메소드를 마치기 직전에 테스트가 변경하거나 추가한 데이터를 모두 원래 상태로 복원
	 *  2. 테스트하기 전에 테스트 실행에 문제가 되지 않는 상태를 만들어주기
	 */
	@Test
	public void addAndGet() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		
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
		
		assertThat(dao.getCount(), is(1));
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		// 1. 등록
		dao.add(user2);
		// 2. 조회
		User userInserted2 = dao.get(user2.getId());
				
		assertThat(userInserted2.getName(), is(user2.getName()));
		assertThat(userInserted2.getPassword(), is(user2.getPassword()));
	}
}
