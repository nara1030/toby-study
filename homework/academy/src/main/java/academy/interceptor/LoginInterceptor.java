package academy.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import academy.common.AcademyConst.USER_GROUP_CD;
import academy.user.User;
import academy.user.UserDaoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
	private UserDaoService userDaoService;
	
	public LoginInterceptor(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("********* 로그인 Interceptor *********");
		// 세션 통해 로그인 여부 확인
		HttpSession httpSession = request.getSession();
		if (Objects.nonNull(httpSession.getAttribute("user"))) {
			return true;
		}
		
		String userId = (String) request.getParameter("userId");
		String userPassword = (String) request.getParameter("userPassword");
		
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userPassword)) {
			log.info("로그인 후 서비스를 이용할 수 있습니다.");
			return false;
		}
		
		User user = new User(userId, userPassword, null, null);
		user = userDaoService.findOne(user);
		
		if (Objects.isNull(user)) {
			log.info("입력하신 정보의 사용자가 존재하지 않습니다.");
			return false;
		}
		
		// 세션에 로그인 정보 담아주기
		httpSession = request.getSession(true);
		httpSession.setAttribute("user", user);
		System.out.println(httpSession.getAttribute("user"));
		
		if (USER_GROUP_CD.ADMIN.CODE.equals(user.getUserGroupCd())) {
			log.info("환영합니다, {} 관리자님", user.getUserNm());
		} else if (USER_GROUP_CD.PROFESSOR.CODE.equals(user.getUserGroupCd())) {
			log.info("환영합니다, {} 교수님", user.getUserNm());
		} else if (USER_GROUP_CD.STUDENT.CODE.equals(user.getUserGroupCd())) {
			log.info("환영합니다, {} 학생님", user.getUserNm());
		} else {
			log.info("환영합니다, ? 님");
		}
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
