package control.commands;

import logic.Game;


public class SuperCoinsCommand extends NoParamsCommand{

	private static final int SUPER_COINS = 1000;
	
	public SuperCoinsCommand() {
		super("coins", "c", "[c]oins", "immediately gives the player 1000 coins");
	}

	
	@Override
	public boolean execute(Game game) {
		game.superCoins(SUPER_COINS);
		game.setIncrementCycles(false);
		return true;
	}

	
	
}
