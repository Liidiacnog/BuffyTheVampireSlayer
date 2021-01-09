package exceptions;

/*thrown when an attempt is made to add Dracula to the board in spite of the fact that he is already present.*/

public class DraculaHasArisenException extends Exception {

	public DraculaHasArisenException() {
		super();
	}
	
	public DraculaHasArisenException(String str) {
		super(str);
	}
	
	public DraculaHasArisenException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public DraculaHasArisenException(Throwable cause) {
		super(cause);
	}
	
	public DraculaHasArisenException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}
}
