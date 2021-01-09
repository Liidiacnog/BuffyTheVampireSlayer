package exceptions;

/* thrown when an attempt is made to execute any command which has a cost in coins but the user does not have enough 
 * coins to cover that cost. */

public class NotEnoughCoinsException extends Exception {

	public NotEnoughCoinsException() {
		super();
	}
	
	public NotEnoughCoinsException(String str) {
		super(str);
	}
	
	public NotEnoughCoinsException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public NotEnoughCoinsException(Throwable cause) {
		super(cause);
	}
	
	public NotEnoughCoinsException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}
}