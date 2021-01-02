package exceptions;

public class GameException extends Exception {
	
	private String errorMsg;

	public GameException(String str) {
		errorMsg = str;
	}
	
	public String toString() {
		return errorMsg;
	}
}
