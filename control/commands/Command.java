package control.commands;

import logic.Game;


public abstract class Command {

	  private final String name, shortcut, details, help;

	  protected static final String incorrectNumberOfArgsMsg = "Incorrect number of arguments", incorrectArgsMsg = "Incorrect arguments format";
	  
	  public Command(String name,  String shortcut, String details, String help){    
	    this.name = name;
	    this.shortcut = shortcut;
	    this.details = details;
	    this.help = help;
	  }
	  
	  //return true if no refresh of the display is needed (example: help, reset, exit, addSlayer when it hasn't been added,...)
	  public abstract boolean execute(Game game);
	  
	  public abstract Command parse(String[] commandWords);
	  
	  protected boolean matchCommandName(String name) {
		    return shortcut.equalsIgnoreCase(name) || 
		        this.name.equalsIgnoreCase(name);
	  }
	  
	  public String helpText(){
	    return details + ": " + help + "\n";
	  }
}
