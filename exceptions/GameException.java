package exceptions;

public class GameException extends Exception {
	
	public GameException() {
		super();
	}
	
	public GameException(String str) {
		super(str);
	}
	
	public GameException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public GameException(Throwable cause) {
		super(cause);
	}
	
	public GameException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}
	
	
}
