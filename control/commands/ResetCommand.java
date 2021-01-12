package control.commands;

import logic.Game;

public class ResetCommand extends NoParamsCommand {

	private static final String ResetCommandName = "reset";
	private static final String ResetCommandShortcut = "r";
	private static final String ResetCommandDetails = "[r]eset";
	private static final String ResetCommandHelp = "reset game";

	
	public ResetCommand() {
		super(ResetCommandName, ResetCommandShortcut, ResetCommandDetails, ResetCommandHelp);
	}

	@Override
	public boolean execute(Game game) {
		game.resetCommand();
		return true;
	}

}

