package v1_ch02.vr5;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import v1_ch01.util.vo.User;
import v1_ch01.vr3_composition.dao.UserDao;
import v1_ch01.vr5_ioc_spring.factory.DaoFactory;

public class UserDaoTest {
	/**
	 * 테스트 코드 개선(리팩토링)
	 *  - 애플리케이션 코드만이 리팩토링의 대상은 아니다.
	 *  - 중복된 코드는 별도의 메소드로 뽑아내는 것이 가장 손쉬운 방법이다(Helper 메소드: @Before 혹은 private).
	 * 
	 * JUnit이 하나의 테스트 클래스를 가져와 테스트를 수행하는 방식은 다음과 같다(반복: 2 ~ 5).
	 *  1. 테스트 클래스에서 @Test 가 붙은 public이고 void형이며 파라미터가 없는 테스트 메소드를 모두 찾는다.
	 *  2. 테스트 클래스의 오브젝트를 하나 만든다.
	 *  3. @Before 가 붙은 메소드가 있으면 실행된다.
	 *  4. @Test 가 붙은 메소드를 하나 호출하고 테스트 결과를 저장해둔다.
	 *  5. @After 가 붙은 메소드가 있으면 실행한다.
	 *  6. 나머지 테스트 메소드에 대해 2~5번을 반복한다.
	 *  7. 모든 테스트의 결과를 종합해서 돌려준다.
	 * 
	 * 그런데, 왜 테스트 메소드를 실행할 때마다 새로운 오브젝트를 만드는 것일까?
	 *  - JUnit 개발자는 각 테스트가 서로 영향을 주지 않고 독립적으로 실행됨을 확실히 보장해주기 위해 새로운 오브젝트를 만들게 했다.
	 *  - 따라서 어차피 다음 테스트 메소드가 실행될 때는 새로운 오브젝트가 만들어져 다 초기화될 것이다.
	 */
	private UserDao dao;
	
	@Before
	public void setUp() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		this.dao = applicationContext.getBean("userDao", UserDao.class);
		
//		System.out.println(applicationContext);
//		System.out.println(this);
//		System.out.println(this.dao);
	}
	
	@Test
	public void count() {
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		
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
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		
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
