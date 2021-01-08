package exceptions;

public class CommandParseException extends GameException {

	public CommandParseException() {
		super();
	}
	
	public CommandParseException(String str) {
		super(str);
	}
	
	public CommandParseException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public CommandParseException(Throwable cause) {
		super(cause);
	}
	
	public CommandParseException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}
	
	
}
