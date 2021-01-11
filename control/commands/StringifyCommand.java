package control.commands;

import logic.Game;

public class StringifyCommand extends NoParamsCommand {

	private static final String name = "stringify";
	private static final String shortcut = "t";
	private static final String details = "s[t]ringify";
	private static final String help = "serialize the state of the game as a text stream";

	
	public StringifyCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.stringifyCommand();
		return false;
	}

}
