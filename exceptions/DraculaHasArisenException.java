package exceptions;

/*thrown when an attempt is made to add Dracula to the board in spite of the fact that he is already present.*/

public class DraculaHasArisenException extends CommandExecuteException {

	public DraculaHasArisenException(String str) {
		super(str);
	}

}
