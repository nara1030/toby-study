package academy.student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import academy.common.AcademyConst.USER_GROUP_CD;
import academy.user.User;
import academy.user.UserDaoService;

@Service
public class StudentDaoService {
	private List<Student> students = new ArrayList<Student>(); // JRE 말고 JDK를 1.8 이상으로 올려야..
	private UserDaoService userDaoService;
	
	public StudentDaoService(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	public Student findOne(final Student student) {
		return filterStudentWithUser().stream()
									  .filter(std -> std.getStudentId().equals(student.getStudentId()))
									  .findFirst()
									  .orElse(null);
	}
	
	public void add(final User user, final Student student) {
		userDaoService.add(user);
		students.add(student);
	}
	
	private List<Student> filterStudentWithUser() {
		return userDaoService.findAll()
							 .stream()
							 .filter(user -> USER_GROUP_CD.STUDENT.CODE.equals(user.getUserGroupCd()))
							 .map(user -> new Student(user.getUserId(), user.getUserNm()))
							 .collect(Collectors.toList());
	}
}
