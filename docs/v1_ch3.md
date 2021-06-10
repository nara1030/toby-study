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

* 
*

- - -
```txt
1. xml 경로 설정 이슈
  - ex. http://localhost:8081/academy/users
  - 위 경로로 정보를 불러오고자 할 때 프론트 컨트롤러의 URL을 설정해주어야 한다.
    즉 web.xml 및 하위(?)의 dispatcher-servlet.xml에 경로를 설정해주어야 한다.
    내 경우 위 경로로 요청을 보냈는데 인터셉터를 안 타는 문제가 있었는데, 각 설정은 아래와 같았다.
  - web.xml
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/academy/*</url-pattern>
    </servlet-mapping>
    dispatcher-servlet.xml
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/academy/*"/>
            <ref bean="loginInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
  - 인터셉터의 경로를 /academy/* 에서 /*로 바꿔주자 해결되었다.
2. getParameter vs. getAttribute
3. 인터셉터가 여러 개라면, 순서는?
  - https://sjh836.tistory.com/163
4. 인터셉터 vs. 필터
  - https://datamod.tistory.com/128
5. ContextLoaderListener vs. DispatcherServlet
  - https://howtodoinjava.com/spring-mvc/contextloaderlistener-vs-dispatcherservlet/
  - https://www.tutorialspoint.com/difference-between-dispatcherservlet-and-contextloaderlistener-in-spring
  - https://www.baeldung.com/spring-web-contexts
  - https://pangtrue.tistory.com/136

```

##### [목차로 이동](#목차)