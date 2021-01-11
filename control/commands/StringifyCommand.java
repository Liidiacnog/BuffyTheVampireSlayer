package control.commands;

import logic.Game;

public class StringifyCommand extends NoParamsCommand {

	public StringifyCommand() {
		super("stringify", "t", "s[t]ringify", "serialize the state of the game as a text stream");
	}

	@Override
	public boolean execute(Game game) {
		game.stringifyCommand();
		return false;
	}

}
