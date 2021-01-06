package control.commands;

import exceptions.CommandExecuteException;
import logic.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("exit", "e", "[e]xit", "exit game");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.setIncrementCycles(false);
		game.exit();
		return false;
	}


}
