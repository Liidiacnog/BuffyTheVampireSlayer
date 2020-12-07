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
				new UpdateCommand(),
				new AddBloodBankCommand()
		};
		
		/*passes the minimally processed input text to an object of each of the concrete command classes, 
		 * in order to see which of them accepts it as valid text for that command*/
		public static Command parseCommand (String[] input, Controller controller) {
			Command obj = null;
			int i = 0; 
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
