package control.commands;

import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import logic.Game;


public class LightFlashCommand extends NoParamsCommand{

	private final static int cost = 50;
	
	public LightFlashCommand() {
		super("light", "l", "[l]ight", "costs 50 coins. Eliminates all the vampires, except Dracula if present, from the board");
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
