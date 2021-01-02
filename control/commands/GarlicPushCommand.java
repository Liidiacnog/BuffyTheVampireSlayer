package control.commands;

import exceptions.GameException;
import logic.Game;


public class GarlicPushCommand extends NoParamsCommand {

	private final static int cost = 10;
	
	public GarlicPushCommand() {
		super("garlic push", "g", "[g]arlic", "costs 10 coins. Pushes all the vampires back one tile");
	}

	
	@Override
	public boolean execute(Game game) throws GameException {
		boolean exec = false;
		if(game.garlicPush(cost)) {
			game.setNewGameCycle(true);
			game.setIncrementCycles(true);
			exec = true;
		}else
			game.setIncrementCycles(false);
		
		return exec;
	}

	
}