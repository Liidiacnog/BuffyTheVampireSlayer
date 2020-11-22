package utils;

import control.commands.*;
import control.*;

//utility class: all its methods are static
public class CommandGenerator {

		private static Command[] availableCommands = {
				new AddCommand(),
				new HelpCommand(),
				new ResetCommand(),
				new ExitCommand(),
				new UpdateCommand()
		};
		
		
		/*to know which command to execute, the controller run method, after splitting the text entered by the user at the console
		into words, calls a method called, say, parseCommand of the utility class CommandGenerator, passing the 
		minimally-processed input text as a parameter.  
		The parseCommand method then passes this minimally-processed input text to an object of each of the concrete 
		command classes in turn, in order to see which of them accepts it as valid text for that command.
		That is, each concrete subclass of the Command class has a parse method which checks
		to see if the minimally-processed input text passed as a parameter corresponds to a use of the command that the
		class represents and the parseCommand method calls each of these parse methods in turn.
		If the parse method of a given concrete subclass of the Command class accepts the input text as valid, it returns an object
	 	of that concrete subclass, otherwise it returns the value null. 
	 	If none of the parse methods of the concrete subclasses of the Command class accepts the input text as valid, i.e. all of them return the value null to the parseCommand method of
	   	the CommandGenerator, this latter method also returns the value null to the controller, which then informs the user that
	    the text entered did not correspond to any known command.
		With this mechanism, if the text entered by the user corresponds to a command of the system, the controller obtains an 
		object of the class representing that command, which it can then use to execute the command (each concrete subclass of 
		the Command class also has a method called execute). Notice that we could now add new commands without having to modify
	 	the existing code (except to let the CommandGenerator know the name of the new subclass) by simply adding new concrete
	  	subclasses.
		*/
		
		
		/*passes the minimally processed input text to an object of each of the concrete command classes, 
		 * in order to see which of them accepts it as valid text for that command*/
		public static Command parseCommand (String[] input, Controller controller) {
			Command obj = null;
			int i = 0; 
			/*If the parse method of a given concrete subclass of the Command class accepts the input text as valid, 
			 * it returns an object	of that concrete subclass, otherwise it returns the value null. */
			while(i < availableCommands.length && availableCommands[i].parse(input) == null) {
				++i; 
			}
			if(availableCommands[i].parse(input) != null) {
				obj = availableCommands[i].parse(input);
			}
			
			return obj;
		}
		
		
		/*has a similar structure to the parseCommand() method but calling the helpText() method of each of the Command subclasses
		 * in turn. This method is called by the execute method of the HelpCommand class.*/
		public static String commandHelp() {
			String str = String.format("Available commands:%n");
			int i = 0; 
			while(i < availableCommands.length) {
				str += availableCommands[i].helpText();
				++i; 
			}
			
			return str;
		}
	
}
