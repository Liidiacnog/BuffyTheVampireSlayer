package control.commands;

import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import logic.Game;


public class GarlicPushCommand extends NoParamsCommand {

	private final static int cost = 10;
	
	public GarlicPushCommand() {
		super("garlic push", "g", "[g]arlic", "costs 10 coins. Pushes all the vampires back one tile");
	}

	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean exec = false;
		try{
			if(game.garlicPush(cost)) {
				game.setNewGameCycle(true);
				game.setIncrementCycles(true);
				exec = true;
			}else
				game.setIncrementCycles(false);
		}catch (NotEnoughCoinsException lowLevel){
			throw new CommandExecuteException("[ERROR] Failed to release garlic push", lowLevel);
		}
		return exec;
	}

	
}