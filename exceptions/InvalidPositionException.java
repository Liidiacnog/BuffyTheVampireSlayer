package exceptions;

public class InvalidPositionException extends CommandExecuteException {

	public InvalidPositionException(String element, int x, int y) {
		super("[DEBUG] Position (" + x + ", " + y + "): Invalid position", element);
	}

}
//"[ERROR] failed to add " + element + "%n" + 