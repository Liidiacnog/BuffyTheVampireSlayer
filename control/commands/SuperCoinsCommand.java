package control.commands;

import logic.Game;


public class SuperCoinsCommand extends NoParamsCommand{

	private static final int SUPER_COINS = 1000;
	private static final String SuperCoinsCommandName = "coins";
	private static final String SuperCoinsCommandShortcut = "c";
	private static final String SuperCoinsCommandDetails = "[c]oins";
	private static final String SuperCoinsCommandHelp = "immediately gives the player 1000 coins";

	
	public SuperCoinsCommand() {
		super(SuperCoinsCommandName, SuperCoinsCommandShortcut, SuperCoinsCommandDetails, SuperCoinsCommandHelp);
	}

	
	@Override
	public boolean execute(Game game) {
		game.superCoinsCommand(SUPER_COINS);
		return true;
	}

	
	
}
