package control.commands;

import logic.Game;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *If no type is indicated, the vampire placed on the board is a normal vampire, 
 *if the type is D, it is Dracula (as long as he is not already on the board), 
 *and if the type is E, it is an explosive vampire. 
 *This command does not cause the game to evolve (and the cycle number
does not change) and if there are no vampires remaining, it has no effect.
 */


public class AddVampireCommand extends Command{

		private int x, y;
		private String type;
		//ArrayList of String because commandWords is a String array and we need to compare them
		private ArrayList<String> availableTypes = new ArrayList<>(Arrays.asList( "D", "E"));
				//Dracula, ExplosiveVampire, if no type is specified => normal Vampire //TODO ' ' OR ''?, does it work? respuesta: No funciona ninguna
		
		public AddVampireCommand() {
			super("add a vampire", "v", "[v]ampire [<type>] <x> <y>", "add a slayer in position x, y");
		}
		
		public AddVampireCommand(int xCoord, int yCoord, String type) {
			this(); //calls AddCommand constructor defined in this subclass, on a previous line
			x = xCoord;
			y = yCoord;
			this.type = type.toUpperCase();
		}

		
		@Override
		public boolean execute(Game game) {
			game.setIncrementCycles(false);
			if(availableTypes.indexOf(type) == 0)
				game.addDracula(x, y);
			else if(availableTypes.indexOf(type) == 1)
				game.addExplosiveVampire(x, y);
			else //always called when type matches to one of the available ones, so last case is always normal vampire
				game.addVampire(x, y);
			return true;
		}

		
		@Override
		public Command parse(String[] commandWords) {
			AddVampireCommand command = null;
			if (matchCommandName(commandWords[0])) {//TODO falta lo de try
				if (availableTypes.indexOf(commandWords[1].toUpperCase()) != -1) //it has been found in the ArrayList
					command = new AddVampireCommand(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]), commandWords[1]);
				else 
					command = new AddVampireCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), "");
			}
			return command;
		}
}




/*private boolean matchType(String c) {
			boolean match = false;
			int i = 0;
			while (i < availableTypes.length && c != availableTypes[i])
				++i;
			if(c == availableTypes[i])
				match = true;
			return match;
		}*/ //use of indexOf
