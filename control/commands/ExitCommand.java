package control.commands;

import logic.Game;

public class ExitCommand extends NoParamsCommand {

	
	private static final String ExitCommandName = "exit";
	private static final String ExitCommandShortcut = "e";
	private static final String ExitCommandDetails = "[e]xit";
	private static final String ExitCommandHelp = "exit game";

	
	public ExitCommand() {
		super(ExitCommandName, ExitCommandShortcut, ExitCommandDetails, ExitCommandHelp);
	}

	@Override
	public boolean execute(Game game)  {
		game.exitCommand();
		return false;
	}


}
