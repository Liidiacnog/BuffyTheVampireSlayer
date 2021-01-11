package control.commands;

import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import logic.Game;


public class GarlicPushCommand extends NoParamsCommand {

	private final static int cost = 10;
	private static final String name = "garlic";
	private static final String shortcut = "g";
	private static final String details = "[g]arlic";
	private static final String help = "costs 10 coins. Pushes all the vampires back one tile";

	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
	}

	
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean exec = false;
		try{
			if(game.garlicPushCommand(cost))
				exec = true;
		}catch (NotEnoughCoinsException lowLevel){
			throw new CommandExecuteException("[ERROR] Failed to release garlic push", lowLevel);
		}
		return exec;
	}

	
}