package control.commands;

import exceptions.CommandExecuteException;
import logic.Game;

public class StringifyCommand extends NoParamsCommand {

	public StringifyCommand() {
		super("stringify", "t", "S[t]ringify", "convert state of the game into a string");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		System.out.println(game.stringify());
		return false;
	}

}
