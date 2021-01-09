package exceptions;

/*thrown when an attempt is made to add a vampire to the board when there are no more vampires left to come out.*/

public class NoMoreVampiresException extends Exception {

	public NoMoreVampiresException() {
		super();
	}
	
	public NoMoreVampiresException(String str) {
		super(str);
	}
	
	public NoMoreVampiresException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public NoMoreVampiresException(Throwable cause) {
		super(cause);
	}
	
	public NoMoreVampiresException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}

}
