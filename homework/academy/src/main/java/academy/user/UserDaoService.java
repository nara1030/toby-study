package academy.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import academy.common.AcademyConst.USER_GROUP_CD;

@Service
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User("admin", "admin", "관리자", USER_GROUP_CD.ADMIN.CODE));
		users.add(new User("pro1", "pro1", "교수1", USER_GROUP_CD.PROFESSOR.CODE));
		users.add(new User("stu1", "stu1", "학생1", USER_GROUP_CD.STUDENT.CODE));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(final User user) {
		return users.stream()
					.filter(usr -> usr.getUserId().equals(user.getUserId()) && usr.getUserPassword().equals(user.getUserPassword()))
					.findFirst()
					.orElse(null);
	}
	
	public User findOne(final String userId) {
		return users.stream()
					.filter(usr -> usr.getUserId().equals(userId))
					.findFirst()
					.orElse(null);
	}
	
	public void add(final User user) {
		users.add(user);
	}
}
