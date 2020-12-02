package control;

import java.util.Scanner;

import control.commands.Command;
import utils.CommandGenerator;
import logic.Game;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void printGame() {
   	 	System.out.println("" + game);
   }
    
    public void run() {
	    boolean refreshDisplay = true;

	    while (!game.isFinished()){
	    		
    		 if (refreshDisplay) printGame();
    		 refreshDisplay = false;
        		 
			  System.out.print(prompt);	
			  String s = scanner.nextLine();
			  String[] parameters = s.toLowerCase().trim().split(" ");
			  System.out.println("[DEBUG] Executing: " + s);
		      Command command = CommandGenerator.parseCommand(parameters, this);
		      if (command != null) { 
	    	  		refreshDisplay = command.execute(game);
	    	  		if(refreshDisplay)
	    	  			game.refreshDisplay();
		       }
		       else {
		    	   		System.out.println("[ERROR]: "+ unknownCommandMsg);
		       }
		}	    
    	if (refreshDisplay) printGame();
		System.out.println ("[Game over] " + game.getWinnerMessage());

    }

    
    
}


/*
//manages input of the user, returns char which tells run() which action to carry out
public char userCommand(String str) {
	char output = '0';
	str = str.toLowerCase();
	if (str.equals("h") || str.equals("help")) {
		output = 'h';
	} else if (str.equals("r") || str.equals("reset")) {
		reset();
		output = 'r'; // r of reset
	} else if (str.equals("e") || str.equals("exit")) {
		output = 'e';
	}  else if (str.equals("n") || str.equals("none") || str.equals("")) {
		output = 'c'; // c of correct
	} else if (str.startsWith("a ") || str.startsWith("add ")) {
		String[] parts = str.split(" ");
		try {
			int x = Integer.parseInt(parts[1]), y = Integer.parseInt(parts[2]);
			if (x != level.getColumns() - 1 && board.isFree(x, y)) { //cannot add slayer on last column 
				if (board.canAfford(player.getCoins()) != -1) {
					board.addSlayer(x, y, this); 
					player.payCoins(board.canAfford(player.getCoins()));
				} else {
					System.out.println(player.toStringNotEnoughCoins());
				}
				output = 'c';
			} else {
				output = 'p'; // p of (invalid) position
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			output = 'p';
		} catch (NumberFormatException nfe) {
			output = 'p';
		}
		
	}else
		output = 'i'; // i of invalid
	
	
	return output;
}
*/

//OLD CODE (OURS)
    //in charge of the loop which keeps the game going
    /*while the game is not finished, the state of the game is printed on the console, the user is prompted 
     * for a command and the command is executed*/
    /*public void run() {
    	String str = "";
    	char ok = 0;
    	while (ok != 'e') {
	   		printGame();
	   		System.out.println("");
	   		System.out.print(prompt);
			str = in.nextLine();
	    	ok = game.userCommand(str);
	    	while (ok != 'c' && ok != 'e' && ok != 'r') {
	    		if (ok == 'i') {
	    			System.out.println();
	        		System.out.println(invalidCommandMsg);
	    		} else if (ok == 'p') {
	    			System.out.println(invalidPositionMsg);
	    		}
				System.out.println(helpMsg);
				System.out.print(prompt);
				str = in.nextLine();
	    		ok = game.userCommand(str);
			}
	    	if (ok != 'e' && ok != 'r'){
				game.update();
				game.attack();
				game.addVampire();
				game.removeDeadObj();
				str = game.checkEnd();
				if (!str.equals("")) {
					ok = 'e';
				}
				game.incrementCycles();
	    	} else {
	    		str = "[Game over] Nobody wins...";
	    	}
    	}
		printGame();
		System.out.print(str);
    }

}*/