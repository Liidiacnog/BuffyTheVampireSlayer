package exceptions;


/* thrown when coordinates provided by the user denote a tile that is already occupied or that is not on the board.*/

public class InvalidPositionException extends Exception {

	public InvalidPositionException() {
		super();
	}
	
	public InvalidPositionException(String str) {
		super(str);
	}
	
	public InvalidPositionException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public InvalidPositionException(Throwable cause) {
		super(cause);
	}
	
	public InvalidPositionException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}

}