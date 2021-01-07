package control.commands;

import exceptions.CommandExecuteException;
import exceptions.GameException;
import logic.Game;


public class LightFlashCommand extends NoParamsCommand{

	private final static int cost = 50;
	
	public LightFlashCommand() {
		super("light flash", "l", "[l]ight", "costs 50 coins. Eliminates all the vampires, except Dracula if present, from the board");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean exec = false;
		try{
			if(game.lightFlash(cost)) {
				game.setIncrementCycles(true);
				game.setNewGameCycle(true);
				exec = true;
			} else
				game.setIncrementCycles(false);
		}catch (CommandExecuteException lowLevel){
			throw new CommandExecuteException("[ERROR] Failed to release light flash", lowLevel);
		}
		return exec;
	}
	
}
