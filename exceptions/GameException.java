package exceptions;

public class GameException extends Exception {
	
	private String errorMsg;

	public GameException(String str) {
		errorMsg = str;
	}
	
	public String getMessage() {
		return errorMsg;
	}
}
