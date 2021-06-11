package academy.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.common.AcademyController;
import academy.student.Student;
import academy.student.StudentDaoService;
import academy.common.AcademyConst.USER_GROUP_CD;

@RestController
@RequestMapping("/users")
public class UserController extends AcademyController {
	private UserDaoService userDaoService;
	private StudentDaoService studentDaoService;
	
	public UserController(UserDaoService userDaoService, StudentDaoService studentDaoService) {
		this.userDaoService = userDaoService;
		this.studentDaoService = studentDaoService;
	}
	
	@GetMapping("")
	public List<User> retrieveAllUser() {
		return userDaoService.findAll();
	}
	
	@GetMapping(value = "/{userId}")
	public User findUserByUserId(@PathVariable String userId) {
		return userDaoService.findOne(userId);
	}
	
	@PostMapping("")
	public void addUser(HttpServletResponse response, @RequestBody User user) throws IOException {
		/*
		 * 1. validate 추가 필요
		 * 2. 예외 처리 필요
		 */
		if (USER_GROUP_CD.STUDENT.CODE.equals(user.getUserGroupCd())) {
			studentDaoService.add(user, new Student(user.getUserId(), user.getUserNm()));
		} else if (USER_GROUP_CD.PROFESSOR.CODE.equals(user.getUserGroupCd())) {
		} else {
		}
		
		response.sendRedirect("users");
	}
}
