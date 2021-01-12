package control.commands;

import logic.Game;

public class HelpCommand extends NoParamsCommand {

	private static final String HelpCommandName = "help";
	private static final String HelpCommandShortcut = "h";
	private static final String HelpCommandDetails = "[h]elp";
	private static final String HelpCommandHelp = "show this help";

	
	public HelpCommand() {
		super(HelpCommandName, HelpCommandShortcut, HelpCommandDetails, HelpCommandHelp);
	}

	
	@Override
	public boolean execute(Game game) {
		game.helpCommand();
		return false; 
	}

}
