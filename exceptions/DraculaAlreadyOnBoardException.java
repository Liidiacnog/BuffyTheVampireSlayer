package exceptions;

public class DraculaAlreadyOnBoardException extends CommandExecuteException {

	public DraculaAlreadyOnBoardException() {
		super("[DEBUG] Dracula is already on board", "vampire");
	}

}
