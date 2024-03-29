package control.commands;

import exceptions.*;
import logic.Game;


public abstract class Command {

	  protected final String name, shortcut, details, help;

	  
	  public Command(String name,  String shortcut, String details, String help){    
	    this.name = name;
	    this.shortcut = shortcut;
	    this.details = details;
	    this.help = help;
	  }
	  
	  //return true if no refresh of the display is needed (example: help, reset, exit, addSlayer when it hasn't been added,...)
	  public abstract boolean execute(Game game) throws CommandExecuteException; //throws it because each execute method is in charge of wrapping low-level exceptions it catches, in it 
	  
	  public abstract Command parse(String[] commandWords) throws CommandParseException; 
	  
	  protected boolean matchCommandName(String name) {
		    return shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	  }
	  
	  public String helpText(){
	    return details + ": " + help + "\n";
	  }
}
