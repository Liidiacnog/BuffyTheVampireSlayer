package exceptions;

public class InvalidArgumentsException extends CommandParseException {

	public InvalidArgumentsException() {
		super();
	}
	
	public InvalidArgumentsException(String str) {
		super(str);
	}
	
	public InvalidArgumentsException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public InvalidArgumentsException(Throwable cause) {
		super(cause);
	}
	
	public InvalidArgumentsException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}

}
