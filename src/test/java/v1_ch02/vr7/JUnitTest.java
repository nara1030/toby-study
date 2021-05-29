package v1_ch02.vr7;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import v1_ch01.util.vo.User;
import v1_ch01.vr5_ioc_spring.factory.DaoFactory;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration
@ContextConfiguration(classes= {User.class})
public class JUnitTest {
	static JUnitTest testObject;
	static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
	
	@Autowired
	ApplicationContext context;
	static ApplicationContext contextObject = null;
	
	@Test
	public void test1() {
		assertThat(this, is(not(testObject)));
//		assertThat(this, is(not(sameInstance(testObject)))); // testObject == null
		testObject = this;
	}
	
	@Test
	public void test2() {
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}
	
	@Test
	public void test3() {
		assertThat(this, is(not(sameInstance(testObject))));
		testObject = this;
	}
	
	// 이하는 개선된 테스트 코드
	
	@Test
	public void test4() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test
	public void test5() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test
	public void test6() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
//		assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
		assertThat(contextObject, either(is(nullValue())).or(is((Object) this.context)));
		contextObject = this.context;
	}
}
