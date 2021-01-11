package control.commands;

import logic.Game;

public class HelpCommand extends NoParamsCommand {

	private static final String name = "help";
	private static final String shortcut = "h";
	private static final String details = "[h]elp";
	private static final String help = "show this help";

	
	public HelpCommand() {
		super(name, shortcut, details, help);
	}

	
	@Override
	public boolean execute(Game game) {
		game.helpCommand();
		return false; 
	}

}
