package control.commands;

import logic.Game;

public class ResetCommand extends NoParamsCommand{

	help =  "[r]eset: reset game%n";

	public ResetCommand(){ //public Command(String name,  String shortcut, String details, String help){    
		super("Reset", "r", , ); //TODO change?
	}
	
	public boolean execute(Game game) {
		game.reset();
		return true;
	}
	
}
