package exceptions;

public class InvalidVampireTypeException extends Exception {

	public InvalidVampireTypeException() {
		super();
	}
	
	public InvalidVampireTypeException(String str) {
		super(str);
	}
	
	public InvalidVampireTypeException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public InvalidVampireTypeException(Throwable cause) {
		super(cause);
	}
	
	public InvalidVampireTypeException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}

}
