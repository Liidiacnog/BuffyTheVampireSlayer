package logic;
/*
 * Encapsula la lógica del juego. Contiene un GameObjectBoard al que delega la funcionalidad.
Esta clase debe ser quien lleve la puntuación y también guardar una referencia a la instancia de Player.
 representa la partida en curso. Durante la ejecución de la aplicación solo se creará un objeto de la clase Game
 */

/*On each cycle of the game, the following actions are carried out in sequence:

1.	Draw. Sends the current state of the board and other game information to the standard output.
2.	User action. Accept input from the user (to add a new slayer, to simply advance  the game,...)
3.	Update. Move the game elements on the board.
4.	Attack. Check attacks and decrease the lives of the game elements where necessary.
5.	Add vampire. Add a vampire with a probability that depends on the level.
6.	Remove dead objects. Eliminate any game elements that have no lives left.
7.	Check end. Check whether the game has ended.

The player starts the game with 50 coins and on each turn has a probability of 50% of receiving 10 coins.

*/

public class Game {

	//fields
	private static Level lvl;
	
	
	//constructor
	public Game(Long seed, Level lvl) {
		//TODO 
	}
	
	
	void update() {//actualiza el estado de todos los elementos del juego
		
	}
	
	
	
	
}
