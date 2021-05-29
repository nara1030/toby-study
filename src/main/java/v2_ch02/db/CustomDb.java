package v2_ch02.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import v1_ch01.util.exception.FailToConnectDbException;
import v1_ch01.util.exception.FailToGetUserByIdException;
import v1_ch01.util.vo.User;
import v2_ch02.conn.Connection;

public class CustomDb {
	private static Map<String, User> users = new HashMap<String, User>();

	public void insert(Connection conn, User user) {
		if (conn == null) {
			System.out.println("DB 연결 에러로 인한 입력 실패");
			return;
		}

		if (Objects.nonNull(users.get(user.getId()))) {
			System.out.println("DB 데이터 중복으로 인한 입력 실패");
			return;
		}

		users.put(user.getId(), user);
	}

	public User get(Connection conn, String id) throws FailToConnectDbException, FailToGetUserByIdException {
		if (conn == null) {
//			System.out.println("DB 연결 에러로 인한 조회 실패");
//			return null;
			throw new FailToConnectDbException("DB 연결 에러로 인한 조회 실패");
		}

		if (Objects.isNull(users.get(id))) {
			throw new FailToGetUserByIdException("해당 Id 사용자 없음");
		}

		return users.get(id);
	}

	// v1_ch02. 테스트 결과의 일관성
	public void deleteAll(Connection conn) {
		if (conn == null) {
			System.out.println("DB 연결 에러로 인한 삭제 실패");
			return;
		}

		users.clear();
	}

	public Integer getCount(Connection conn) {
		if (conn == null) {
			System.out.println("DB 연결 에러로 인한 카운트 실패");
			return null;
		}

		return Integer.valueOf(users.size());
	}
}
