package academy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import academy.common.AcademyConst.USER_GROUP_CD;
import academy.user.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("********* 학생 Interceptor *********");
		User user = (User) request.getSession().getAttribute("user");
		log.info("current user is {}", user);
		
		if (!USER_GROUP_CD.STUDENT.CODE.equals(user.getUserGroupCd())) {
			log.info("해당 사용자는 학생 서비스를 이용할 수 없습니다.");
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
