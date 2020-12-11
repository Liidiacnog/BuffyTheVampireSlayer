package control.commands;

import logic.Game;
import utils.CommandGenerator;

/*Add a “garlic push” command with syntax [g]arlic, which costs 10 coins. It has
the effect of pushing all the vampires back one tile (i.e. one tile to the right) subject
to the following rules:
• Any vampire that has another game element immediately to its right is unaffected.
• Any vampire in the rightmost column (including Dracula) is pushed off the
board and eliminated.
• Any explosive vampire in the rightmost column does not explode when pushed
off the board.
In addition, the garlic push command stuns the vampires causing them not to move
until the next turn (their move counter is reset).
*/


public class GarlicPushCommand extends NoParamsCommand {

	private int cost = 10;
	
	public GarlicPushCommand() {
		super("garlic push", "g", "[g]arlic", "pushes all the vampires back one tile");
	}

	
	@Override
	public boolean execute(Game game) {
		game.setIncrementCycles(false);
		game.garlicPush();
		return true; //TODO true? 
	}

	
	
}