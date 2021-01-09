package utils;

import control.commands.*;
import exceptions.*;


//utility class: all its methods are static
public class CommandGenerator {

		private static Command[] availableCommands = {
				new AddCommand(),
				new HelpCommand(),
				new ResetCommand(),
				new ExitCommand(),
				new UpdateCommand(),
				new GarlicPushCommand(),
				new LightFlashCommand(),
				new AddBloodBankCommand(),
				new SuperCoinsCommand(),
				new AddVampireCommand(), 
				new StringifyCommand()
		};
		
		/*passes the minimally processed input text to an object of each of the concrete command classes, 
		 * in order to see which of them accepts it as valid text for that command*/
		public static Command parseCommand (String[] input) throws CommandParseException {
			Command obj = null;
			int i = 0; 
			try{
				while(i < availableCommands.length && availableCommands[i].parse(input) == null) {
					++i; 
				}
				if (i != availableCommands.length)
					obj = availableCommands[i].parse(input);
				else
					throw new CommandParseException("[ERROR] Unknown command");
			
			}catch(InvalidArgumentsException | InvalidVampireTypeException ex) {
					throw new CommandParseException("[ERROR] Unknown command", ex);
			}
						
			return obj;
		}
		
		
		/*has a similar structure to the parseCommand() method but calling the helpText() method of each of the Command subclasses
		 * in turn. This method is called by the execute method of the HelpCommand class.*/
		public static String commandHelp() {
			String str = String.format("Available commands:%n");
			for(int i = 0; i < availableCommands.length; ++i) {
				str += availableCommands[i].helpText();
			}
			
			return str;
		}
	
}
