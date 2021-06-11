템플릿
=====
## 목차
1. [다시 보는 초난감 DAO](#다시-보는-초난감-DAO)
2. [변하는 것과 변하지 않는 것](#변하는-것과-변하지-않는-것)
3. [JDBC 전략 패턴의 최적화](#JDBC-전략-패턴의-최적화)
4. [컨텍스트와 DI](#컨텍스트와-DI)
5. [템플릿과 콜백](#템플릿과-콜백)
6. [스프링의 JDBCTEMPLATE](#스프링의-JDBCTEMPLATE)
7. [과제](#과제)

### 다시 보는 초난감 DAO



##### [목차로 이동](#목차)

### 변하는 것과 변하지 않는 것



##### [목차로 이동](#목차)

### JDBC 전략 패턴의 최적화



##### [목차로 이동](#목차)

### 컨텍스트와 DI



##### [목차로 이동](#목차)

### 템플릿과 콜백



##### [목차로 이동](#목차)

### 스프링의 JDBCTEMPLATE



##### [목차로 이동](#목차)

### 과제
토비 스프링 1장 내용을 참고해, [간단한 학사관리 프로그램](https://www.notion.so/1-facd22e7b04140ab81dcfc8405428fe3)을 만들어보자. 요구사항은 아래와 같다.

* ERD: 학생 (1-多) 학생수강과목내역 (1-多) 과목
* 기능
	* 생성
		- [x] 학생 생성
		- [ ] 과목 생성
		- [ ] 수강 과목 매칭
	* 조회
		- [x] 특정 학생 정보 조회
		- [ ] 전체 과목 정보 조회
		- [ ] 특정 학생의 수강 과목 학점 조회
	* 알파
		- [ ] 수강 과목 매칭 시 현재 수강 인원 조회
		- [ ] 특정 과목 최고점 획득 학생 조회(?)

처음에 학사관리 프로그램이라고 해서, 수강과 성적부여 두 기능이 필요하다고 생각했다. 즉, 각 학생이냐, 교수냐, 관리자냐에 따라 접근할 수 있는 기능이 다르므로 구현하고자 하는 기능 외[1] 다른 기능이 훨씬 더 많이 포함되어 있다고 느꼈다. 그런데 요구사항을 다시 보니 학생(혹은 관리자)의 관점에서만 생각한 학사 관리 프로그램인 것 같다. 학생이 본인 점수를 부여하나 싶어 좀 애매하긴 한 것 같은데. 어쨌든 내가 생각한 부분은 지금까지 했던 부분 외의 구현이 많은데 그걸 차치하고 테스트를 구현하려니 손이 안 간다는 문제가 있었다. 무언가 구현 순서가 잘못되지 않았나.. 아무튼 구현 소스 및 테스트 결과[2], 찾아본 것들을 아래 남긴다.

- - -
1. 토비의 스프링 1장은 상속보다는 구현, 즉 전략 패턴으로 알려진 디자인 패턴으로 코드를 리팩토링해가며 스프링의 원리인 DI, IoC를 소개한다. 사실 1장을 신경쓰지 않고 구현했지만 결국 스프링 컨테이너를 썼기에 자연스레 DI, IoC가 적용된 코드이긴 하다.
2. 엔드 포인트
```txt
GET http://localhost:8081/academy/login
GET http://localhost:8081/academy/login?userId=stu1&userPassword=stu1
GET http://localhost:8081/academy/users
GET http://localhost:8081/academy/users/stu1
GET http://localhost:8081/academy/students/pro1
GET http://localhost:8081/academy/students/stu1
POST http://localhost:8081/academy/users
  {
    "userId" : "stu2",
    "userPassword" : "stu2",
    "userNm" : "학생2",
    "userGroupCd" : "02"
  }
```

- - -
```txt
1. XML 설정
  1-A. 각 XML 파일 위치 및 역할 파악 필요
    - web.xml
    - dispatcher-servlet.xml
    - ...
  1-B. 경로
    - 스프링 MVC 설정에서 PATH를 줄 때는 기본적으로 웹 애플리케이션의 경로와 DispatcherServlet의 매핑 경로를 빼야 함
      즉, 인터셉터 경로 역시 이를 뺀 뒷 부분을 설정해야 함
    - https://groups.google.com/g/ksug/c/hstj7qgDfFM
  1-C. 스프링 웹 컨텍스트
    - https://www.baeldung.com/spring-web-contexts
    - https://howtodoinjava.com/spring-mvc/contextloaderlistener-vs-dispatcherservlet/
    - https://pangtrue.tistory.com/136
2. 인터셉터 vs. 필터
  2-A. 인터셉터
    - https://datamod.tistory.com/128
    - https://sjh836.tistory.com/163
  2-B. 필터
    -
3. 로그인과 세션
  3-A. POST 혹은 GET
    - https://lng1982.tistory.com/194
    - https://meetup.toast.com/posts/44
  3-B. 인터셉터, 세션 체크 혹은 공유(인터셉터, 컨트롤러 등)
    - https://stackoverflow.com/questions/18791645/how-to-use-session-attributes-in-spring-mvc
    - https://www.baeldung.com/spring-mvc-session-attributes
    - https://www.baeldung.com/spring-mvc-custom-handler-interceptor
    - http://dveamer.github.io/backend/SpringRequestContextHolder.html
4. REST
  4-A. RestController에서 Redirect 필요한 경우
    - https://stackoverflow.com/questions/29085295/spring-mvc-restcontroller-and-redirect
  4-B. Redirect 루트 경로 이슈
    - 변경 전: response.sendRedirect("/users/" + user.getUserId());
      - GET http://localhost:8081/academy/login?userId=stu1&userPassword=stu1 302
        GET http://localhost:8081/users/stu1                                  404
    - 변경 후: response.sendRedirect("users/" + user.getUserId());
      - GET http://localhost:8081/academy/login?userId=stu1&userPassword=stu1 302
        GET http://localhost:8081/academy/users/stu1                          200
  4-C. @Pathvariable 사용 시 로그인 사용자가 해당 경로에 권한이 없을 경우, 즉 본인 조회만 가능한 경우
    - Ugly
      @GetMapping(value = "/{userId}")
      public Student findOneById(@PathVariable("userId") String studentId
              , @SessionAttribute("user") User user) {
          // studentId와 currentUserId 비교 로직
          log.debug("studentId is {}", studentId);
          log.debug("currentUserId is {}", user.getUserId());
          Student student = new Student(studentId, null, null, null);
		
          return studentDaoService.findOne(student);
      }
    - Ideal
      @GetMapping(value = "/{userId}")
      public Student findOneById(@PathVariable("userId") String studentId) {
          Student student = new Student(studentId, null, null, null);
		
          return studentDaoService.findOne(student);
      }
5. 에러
  5-A. 컨트롤러 메소드 관련 @RequestBody 파라미터 이슈
    - 6월 12, 2021 1:53:23 오전 org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver handleHttpMessageNotReadable
      경고: Failed to read HTTP message: org.springframework.http.converter.HttpMessageNotReadableException: Could not read document: No suitable constructor found for type [simple type, class academy.user.User]: can not instantiate from JSON object (missing default constructor or creator, or perhaps need to add/enable type information?)
      at [Source: java.io.PushbackInputStream@4094ea3a; line: 2, column: 5]; nested exception is com.fasterxml.jackson.databind.JsonMappingException: No suitable constructor found for type [simple type, class academy.user.User]: can not instantiate from JSON object (missing default constructor or creator, or perhaps need to add/enable type information?)
      at [Source: java.io.PushbackInputStream@4094ea3a; line: 2, column: 5]
    - https://stackoverflow.com/questions/7625783/jsonmappingexception-no-suitable-constructor-found-for-type-simple-type-class
  5-B.
6. 기타
  - https://eastglow.github.io/back-end/2018/11/05/Spring-Interceptor%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%84%B8%EC%85%98-%EB%B0%8F-%EA%B6%8C%ED%95%9C-%EC%B2%B4%ED%81%AC-%ED%95%98%EA%B8%B0.html
  - https://gangnam-americano.tistory.com/11
```

##### [목차로 이동](#목차)