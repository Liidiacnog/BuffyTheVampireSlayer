package control.commands;

import logic.Game;

public class ResetCommand extends NoParamsCommand {

	public ResetCommand() {
		super("reset", "r", "[r]eset", "reset game");
	}

	@Override
	public boolean execute(Game game) {
		game.setIncrementCycles(false);
		game.reset();
		return true;
	}

}

