package control.commands;

import exceptions.CommandExecuteException;
import exceptions.NotEnoughCoinsException;
import logic.Game;


public class LightFlashCommand extends NoParamsCommand{

	private final static int cost = 50;
	private static final String LightFlashCommandName = "light";
	private static final String LightFlashCommandShortcut = "l";
	private static final String LightFlashCommandDetails = "[l]ight";
	private static final String LightFlashCommandHelp = "costs 50 coins. Eliminates all the vampires, except Dracula if present, from the board";
	
	
	public LightFlashCommand() {
		super(LightFlashCommandName, LightFlashCommandShortcut, LightFlashCommandDetails, LightFlashCommandHelp);
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
