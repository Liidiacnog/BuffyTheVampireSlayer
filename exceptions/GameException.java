package exceptions;

public class GameException extends Exception {
	
	public GameException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public GameException(String str) {
		super(str);
	}
	
	//TODO @Overwrite getCause()?
	
	
}
