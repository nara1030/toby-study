package academy.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AcademyConst {
	@Getter
	@AllArgsConstructor
	public enum GRADE {
		A("4"),
		B("3"),
		C("2");
		
		public final String POINT;
	}
	
	@Getter
	@AllArgsConstructor
	public enum USER_GROUP_CD {
		ADMIN("00"),
		PROFESSOR("01"),
		STUDENT("02");
		
		public final String CODE;
	}
}
