package academy.login;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import academy.common.AcademyController;
import academy.user.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController extends AcademyController {
	@GetMapping("")
	public void login(HttpServletResponse response, @SessionAttribute("user") User user) throws IOException {
		log.info("********* 로그인 Controller *********");
		response.sendRedirect("users/" + user.getUserId());
	}
}
