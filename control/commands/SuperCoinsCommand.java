package control.commands;

import logic.Game;


public class SuperCoinsCommand extends NoParamsCommand{

	private static final int SUPER_COINS = 1000;
	private static final String name = "coins";
	private static final String shortcut = "c";
	private static final String details = "[c]oins";
	private static final String help = "immediately gives the player 1000 coins";

	
	public SuperCoinsCommand() {
		super(name, shortcut, details, help);
	}

	
	@Override
	public boolean execute(Game game) {
		game.superCoinsCommand(SUPER_COINS);
		return true;
	}

	
	
}
