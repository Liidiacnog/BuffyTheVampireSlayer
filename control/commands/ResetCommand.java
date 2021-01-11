package control.commands;

import logic.Game;

public class ResetCommand extends NoParamsCommand {

	private static final String name = "reset";
	private static final String shortcut = "r";
	private static final String details = "[r]eset";
	private static final String help = "reset game";

	
	public ResetCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.resetCommand();
		return true;
	}

}

