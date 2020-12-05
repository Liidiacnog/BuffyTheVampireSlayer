package control.commands;

import logic.Game;
import utils.CommandGenerator;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("help", "h", "[h]elp", "show this help");
	}

	
	@Override
	public boolean execute(Game game) {
		game.setIncrementCycles(false);
		System.out.println(CommandGenerator.commandHelp());//TODO should it print it or just send the String in some way?
		return false; 
	}

}
