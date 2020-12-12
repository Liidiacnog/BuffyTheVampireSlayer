package control.commands;

import logic.Game;


public class LightFlashCommand extends NoParamsCommand{

	private final static int cost = 50;
	
	public LightFlashCommand() {
		super("light flash", "l", "[l]ight", "costs 50 coins. Eliminates all the vampires, except Dracula if present, from the board");
	}
	
	@Override
	public boolean execute(Game game) {
		game.setIncrementCycles(true);
		return game.lightFlash(cost);
	}
	
}
