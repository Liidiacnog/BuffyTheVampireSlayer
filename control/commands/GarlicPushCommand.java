package control.commands;

import logic.Game;
import utils.CommandGenerator;


public class GarlicPushCommand extends NoParamsCommand {

	private final static int cost = 10;
	
	public GarlicPushCommand() {
		super("garlic push", "g", "[g]arlic", "costs 10 coins. Pushes all the vampires back one tile");
	}

	
	@Override
	public boolean execute(Game game) {
		game.setIncrementCycles(true);
		return game.garlicPush(cost);
	}

	
}