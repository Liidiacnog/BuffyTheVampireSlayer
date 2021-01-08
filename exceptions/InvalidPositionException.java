package exceptions;

//TODO (in general) add 5 constructors and make sure low-level exceptions extend Exception?

/* thrown when coordinates provided by the user denote a tile that is already occupied or that is not on the board.*/

public class InvalidPositionException extends CommandExecuteException {

	public InvalidPositionException(String str) {
		super(str); 
	}

}