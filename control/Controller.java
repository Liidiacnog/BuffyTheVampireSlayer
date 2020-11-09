package control;

import java.util.Scanner;
import logic.Game;

public class Controller {

	
	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner in;
    
  //constructor 
	public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.in = scanner;
    }
    
	
    public void printGame() {
   	 System.out.println(game);
   }
    
    
    //in charge of the loop which keeps the game going
    public void run() {
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

}