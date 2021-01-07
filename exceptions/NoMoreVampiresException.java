package exceptions;

/*thrown when an attempt is made to add a vampire to the board when there are no more vampires left to come out.*/

public class NoMoreVampiresException extends CommandExecuteException {

	public NoMoreVampiresException(String str) {
		super(str); 
	}

}
