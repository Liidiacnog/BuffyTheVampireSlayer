package exceptions;

//TODO (in general)  wrap low level into high level exceptions and create constructor with cause and string, and initialize cause!

/* thrown when coordinates provided by the user denote a tile that is already occupied or that is not on the board.*/

public class InvalidPositionException extends CommandExecuteException {

	public InvalidPositionException(String str) {
		super(str); 
	}

}