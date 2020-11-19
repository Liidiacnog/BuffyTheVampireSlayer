package control.commands;

import logic.Game;

public class HelpCommand extends NoParamsCommand {
	
	help = "[h]elp: show this help%n"; //TODO
	
	public static final String details = ""; //TODO what is details?
	
	public HelpCommand(){
		super("Help", "h", details, help); //TODO change name, shortcut?
	}
	
	/*to be implemented by a method which calls some method of the game object passed as a parameter, and may also 
	 * perform some other action*/
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());//TODO should it print it or just send the String in some way
		return true; 
	}
	
}
