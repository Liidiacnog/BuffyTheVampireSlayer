package control.commands;

import logic.Game;

public class ExitCommand extends NoParamsCommand{

	help = "[e]xit: exit game%n";
	
	
	public ExitCommand(){ //public Command(String name,  String shortcut, String details, String help){    
		super("Exit", "e", , ); //TODO change?
	}
	
	public boolean execute(Game game) {
		game.endGame();
		return true;
	}
	
}
