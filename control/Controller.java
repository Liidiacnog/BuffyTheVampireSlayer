package control;

/*
This class controls the execution of the game, printing the prompt on the console and then reading the command provided 
by the user, parsing it and updating the game accordingly. This class needs, at least, the following two attributes:
private Game game; private Scanner in;
The object contained in the "in" attribute is used to read the commands on the standard input, 
i.e. entered by the user via the keyboard. This class has a method public void run() which contains the main loop of 
the program in which, while the game is not finished, the state of the game is printed on the console, the user
 is prompted for a command and the command is executed. Note that there will only ever be one instance of the Controller
  class in the program, which we will refer to as the controller object.
*/
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
    
    public Controller(Game game, Scanner in) {
	    this.game = game;
	    this.in = in;
    }
   
    /*On each cycle of the game, the following actions are carried out in sequence:

    1.	Draw. Sends the current state of the board and other game information to the standard output.
    2.	User action. Accept input from the user (to add a new slayer, to simply advance  the game,...)
    3.	Update. Move the game elements on the board.
    4.	Attack. Check attacks and decrease the lives of the game elements where necessary.
    5.	Add vampire. Add a vampire with a probability that depends on the level.
    6.	Remove dead objects. Eliminate any game elements that have no lives left.
    7.	Check end. Check whether the game has ended.

    */

    public void printGame() {
   	 	System.out.println(game);
   }

    
    
    public void run() {
    	String str;
    	char ok = 0;
    	while (ok != 'e') {
<<<<<<< HEAD
   		printGame();
   		System.out.println("");
   		System.out.print(prompt);
		str = in.nextLine();
    	ok = game.userCommand(str);
    	while (ok != 'c' && ok != 'e') {
    		if (ok == 'i') {
    			System.out.println();
        		System.out.println(invalidCommandMsg);
    		} else if (ok == 'p') {
    			System.out.println(invalidPositionMsg);
=======
    		printGame();
			System.out.print(prompt);
    		str = in.nextLine();
    		ok = game.userCommand(str);
    		while (ok != 'c' && ok != 'e') {
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
>>>>>>> branch 'main' of https://github.com/Liidiacnog/Test.git
    		}
<<<<<<< HEAD
			System.out.println(helpMsg);
			System.out.print(prompt);
			str = in.nextLine();
    		ok = game.userCommand(str);
		}
		if (ok != 'e'){
			game.update();
			game.attack();
			game.addVampire();
			game.removeDeadObj();
			if (game.checkEnd())
				ok = 'e';
			
			game.receiveCoins();
			game.incrementCycles();
=======
    		if (ok != 'e'){
    			game.update();
    			game.attack();
    			game.addVampire();
    			game.removeDeadObj();
    			if (game.checkEnd()) {
    				ok = 'e';
    			}
    			game.receiveCoins();
    			game.incrementCycles();
>>>>>>> branch 'main' of https://github.com/Liidiacnog/Test.git
    		}
    			
    	}
		printGame();
		System.out.print("Game over!");
    }
    
}

