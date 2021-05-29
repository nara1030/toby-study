package v1_ch02.vr3;

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
	 * 앞서 getCount() 메소드를 테스트에 적용하긴 했지만, 한정된 상황(0, 1)에서 결과를 확인했다.
	 *  1. 좀 더 꼼꼼히, 포괄적인 테스트를 진행할 필요가 있다.
	 *  2. 이 테스트 기능을 기존의 addAndGet() 메소드에 추가하는 것은 좋은 방법이 아니다.
	 * 
	 * - JUnit은 하나의 클래스 안에 여러 개의 테스트 메소드가 들어가는 것을 허용한다.
	 *   - 테스트 메소드는 한 번에 한 가지 검증 목적에만 충실한 것이 좋다(메소드도 마찬가지).
	 * - 코드의 수정 후에는 그 수정에 영향을 받을 만한 테스트를 실행하는 것을 잊지 말자.
	 * - JUnit은 특정한 테스트 메소드의 실행 순서를 보장해주지 않는다.
	 *   - 모든 테스트는 실행 순서에 상관 없이 독립적으로 항상 동일한 결과를 낼 수 있도록 해야 한다.
	 * - @Test 가 붙어 있고 public 접근자가 있으며 리턴 값이 void형이고 파라미터가 없다는 조건을 지켜야 한다.
	 */
	@Test
	public void count() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		
//		User user = new User();
//		user.setId("test1");
//		user.setName("테스트");
//		user.setPassword("tt");
//		User user2 = new User();
//		user2.setId("test2");
//		user2.setName("테스트2");
//		user2.setPassword("tt2");
//		User user3 = new User();
//		user3.setId("test3");
//		user3.setName("테스트3");
//		user3.setPassword("tt3");
		User user = new User("test1", "테스트", "tt");
		User user2 = new User("test2", "테스트2", "tt2");
		User user3 = new User("test3", "테스트3", "tt3");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	@Test
	public void addAndGet() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		
//		User user = new User();
//		user.setId("test1");
//		user.setName("테스트");
//		user.setPassword("tt");
//		User user2 = new User();
//		user2.setId("test1");
//		user2.setName("테스트2");
//		user2.setPassword("tt2");
		User user = new User("test1", "테스트", "tt");
		User user2 = new User("test2", "테스트2", "tt2");
		
		// 1. 등록
		dao.add(user);
		dao.add(user2);
		
		assertThat(dao.getCount(), is(2));
		
		// 2. 조회
		User userInserted = dao.get(user.getId());
		assertThat(userInserted.getName(), is(user.getName()));
		assertThat(userInserted.getPassword(), is(user.getPassword()));
		
		User userInserted2 = dao.get(user2.getId());
		assertThat(userInserted2.getName(), is(user2.getName()));
		assertThat(userInserted2.getPassword(), is(user2.getPassword()));
	}
}
