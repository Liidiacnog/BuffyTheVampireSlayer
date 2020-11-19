package control.commands;

import logic.Game;

public class UpdateCommand extends NoParamsCommand{
	help = "[n]one | []: update%n";
	
	public UpdateCommand(){
		super("Update", "n", details, help); //TODO change name, shortcut?
	}
	
	/*to be implemented by a method which calls some method of the game object passed as a parameter, and may also 
	 * perform some other action*/
	public boolean execute(Game game) {
		game.update();
		return true; 
	}
	
	
}
