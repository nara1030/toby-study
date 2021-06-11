package academy.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import academy.common.AcademyController;
import academy.user.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController extends AcademyController {
	private StudentDaoService studentDaoService;
	
	public StudentController(StudentDaoService studentDaoService) {
		this.studentDaoService = studentDaoService;
	}
	
	@GetMapping(value = "/{userId}")
	public Student findOneById(@PathVariable("userId") String studentId
			, @SessionAttribute("user") User user) {
		log.info("studentId is {}", studentId);
		log.info("current userId is {}", user.getUserId());
		/*
		 * 두 가지 조건이 필요
		 * 1. 로그인한 사용자, 즉 세션이 있는 경우만 접근(이거야 인터셉터 때문에 뭐..)
		 * 2. 경로의 학생 아이디와 접속 아이디가 동일, 즉 본인 조회만 가능
		 * 
		 * 헌데, 세션을 비교해주는 로직이 꼭 이 메소드에 필요한가?
		 */
		
		if (!studentId.equals(user.getUserId())) {
			return null;
		}
		
		Student student = new Student(studentId, null);
		return studentDaoService.findOne(student);
	}
}
