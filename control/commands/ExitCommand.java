package control.commands;

import logic.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("exit", "e", "", "[e]xit: exit game");
		// TODO iniciar details
	}

	@Override
	public boolean execute(Game game) {
		game.exit();
		return true;
	}


}
