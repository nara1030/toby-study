package v1_ch02.vr4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import v1_ch01.util.exception.FailToGetUserByIdException;
import v2_ch02.dao.UserDao;
import v2_ch02.factory.DaoFactory;


public class UserDaoTest {
	/**
	 * 테스트를 작성할 때 부정적인 케이스를 먼저 만드는 습관을 들이는 게 좋다.
	 * get(String id) 메소드의 경우라면,
	 *  1. 존재하는 id가 주어졌을 때 해당 레코드를 정확히 가져오는가
	 *  2. 존재하지 않는 id가 주어졌을 때 어떻게 반응할지 결정**
	 * 
	 * 앞서 좀 더 포괄적인 테스트를 위해 테스트 메소드 추가 및 리팩토링을 진행했다.
	 * 하지만 한 가지 빠뜨린 점은 두 번째와 같은 경우인데, 두 가지 방법을 생각할 수 있다.
	 *   1. null과 같은 특별한 값을 리턴
	 *   2. id에 해당하는 정보를 찾을 수 없다고 예외를 던지는 것
	 *  
	 * 여기에선 후자를 이용한 테스트 메소드를 작성해본다.
	 */
	@Test(expected=FailToGetUserByIdException.class)
	public void getUserFailure() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = applicationContext.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		// dao.get("unknown_id");
	}
}
