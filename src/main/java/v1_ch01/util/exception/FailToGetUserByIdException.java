package v1_ch01.util.exception;

public class FailToGetUserByIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public FailToGetUserByIdException(String errorMessage) {
		super(errorMessage);
	}
}
