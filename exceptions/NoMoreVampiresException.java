package exceptions;

public class NoMoreVampiresException extends CommandExecuteException {

	public NoMoreVampiresException() {
		super("[DEBUG] No remaining vampires", "vampire");
	}

}
