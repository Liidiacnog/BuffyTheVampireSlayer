package control.commands;

import logic.Game;

public class StringifyCommand extends NoParamsCommand {

	private static final String StringifyCommandName = "stringify";
	private static final String StringifyCommandShortcut = "t";
	private static final String StringifyCommandDetails = "s[t]ringify";
	private static final String StringifyCommandHelp = "serialize the state of the game as a text stream";

	
	public StringifyCommand() {
		super(StringifyCommandName, StringifyCommandShortcut, StringifyCommandDetails, StringifyCommandHelp);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(game.stringify());
		game.stringifyCommand();
		return false;
	}

}
