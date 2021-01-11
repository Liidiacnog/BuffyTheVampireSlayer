package control.commands;

import logic.Game;

public class ExitCommand extends NoParamsCommand {

	
	private static final String name = "exit";
	private static final String shortcut = "e";
	private static final String details = "[e]xit";
	private static final String help = "exit game";

	
	public ExitCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game)  {
		game.exitCommand();
		return false;
	}


}
