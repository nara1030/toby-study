package v1_ch02.vr6;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import v1_ch01.util.vo.User;
import v1_ch01.vr3_composition.dao.UserDao;
import v1_ch01.vr5_ioc_spring.factory.DaoFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {DaoFactory.class})
public class UserDaoTest {
	/**
	 * 여전히 개선사항이 남아있는데, 바로 애플리케이션 컨텍스트 생성 방식이다.
	 * 
	 * - 문제점
	 *   - @Before 메소드가 테스트 메소드 개수만큼 반복되므로 애플리케이션 컨텍스트도 반복 횟수만큼 생성된다.
	 *   - 만약 빈이 많아지고 복잡해지면 초기 작업 등 애플리케이션 컨텍스트 생성에 적지 않은 시간이 걸릴 수 있다.
	 *     뿐만 아니라 테스트 마칠 때마다 할당된 리소스 등을 깔끔히 정리해주지 않으면 문제를 일으킬 수도 있다.
	 *   - 테스트는 가능한 독립적으로 매번 새로운 오브젝트를 만들어서 사용하는 것이 원칙이지만, 애플리케이션 컨텍스트의 경우 초기화되고 나면 내부의 상태가 바뀌는 일은 거의 없다.
	 *     예를 들어 빈을 생각해보면, UserDao 빈을 가져다가 add(), get() 메소드 등을 사용한다고 해서 UserDao 빈의 상태가 바뀌진 않는다.
	 *     (빈은 싱글톤으로 만들었기 때문에 상태를 갖지 않는다.)
	 * 
	 * - 해결책
	 *   - 스프링은 JUnit을 이용하는 테스트 컨텍스트 프레임워크를 제공한다.
	 *     이를 이용하면 간단한 어노테이션 설정만으로 테스트에서 필요로 하는 애플리케이션 컨텍스트를 모든 테스트가 공유하게 할 수 있다.
	 *   - 어노테이션
	 *     1. @RunWith
	 *     2. @ContextConfiguration
	 * 
	 * - 더 나아가
	 *   1. 테스트 메소드의 컨텍스트 공유
	 *   2. 테스트 클래스의 컨텍스트 공유
	 *    - 스프링 테스트 컨텍스트 프레임워크의 기능은 하나의 테스트 클래스 안에서 애플리케이션 컨텍스트를 공유해주는 것이 전부가 아니다.
	 */
	@Autowired
	private ApplicationContext context;
	
	// @Autowired
	private UserDao dao;
	
	@Before
	public void setUp() {
		this.dao = context.getBean("userDao", UserDao.class);
		
		System.out.println(this.context);
		System.out.println(this);
		System.out.println(this.dao);
	}
	
	@Test
	public void count() {
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
