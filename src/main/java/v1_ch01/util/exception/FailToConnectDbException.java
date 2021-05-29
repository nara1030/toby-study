package v1_ch01.util.exception;

public class FailToConnectDbException extends Exception {
	private static final long serialVersionUID = 1L;

	public FailToConnectDbException(String errorMessage) {
		super(errorMessage);
	}
}
