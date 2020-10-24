package control;

/*
 * Controller: Clase para controlar la ejecución del juego, preguntando al usuario qué quiere hacer y actualizando la partida de acuerdo a lo que éste
 indique. La clase Controller necesita al menos dos atributos privados:

private Game game; private Scanner in;

El objeto in sirve para leer de la consola las órdenes del usuario. La clase Controller implementa el método público public void run() que controla 
el bucle principal del juego. Concretamente, mientras la partida no esté finalizada, solicita órdenes al usuario y las ejecuta.

Durante la ejecución de la aplicación solo se creará un objeto de la clase Controller
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
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
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
    public void run() {
    	String str;
    	int ok = 0;
    	while (ok == 0) {
    		game.draw();
    		
    		
    		
    		str = scanner.nextLine();
        	ok = Integer.parseInt(str);
    	}
    	
    	
    }

}

