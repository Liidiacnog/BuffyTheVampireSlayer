package control.commands;

import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import logic.Game;


public class LightFlashCommand extends NoParamsCommand{

	private final static int cost = 50;
	private static final String name = "light";
	private static final String shortcut = "l";
	private static final String details = "[l]ight";
	private static final String help = "costs 50 coins. Eliminates all the vampires, except Dracula if present, from the board";
	
	
	public LightFlashCommand() {
		super(name, shortcut, details, help);
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean exec = false;
		try{
			if(game.lightFlashCommand(cost))
				exec = true;
		}catch (NotEnoughCoinsException lowLevel){
			throw new CommandExecuteException("[ERROR] Failed to release light flash", lowLevel);
		}
		return exec;
	}
	
}
