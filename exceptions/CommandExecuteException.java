package exceptions;

public class CommandExecuteException extends GameException {

	public CommandExecuteException() {
		super();
	}
	
	public CommandExecuteException(String str) {
		super(str);
	}
	
	public CommandExecuteException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public CommandExecuteException(Throwable cause) {
		super(cause);
	}
	
	public CommandExecuteException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}
	
}
