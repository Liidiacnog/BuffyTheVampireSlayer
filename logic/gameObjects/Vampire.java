package logic.gameObjects;

import logic.Game;


/*Appears in a randomly-chosen row of the rightmost column of the board. Moves one tile to the left every two turns, 
 * as long as the destination tile of the move is free. On each turn, it bites the slayer in the next tile to the left,
 *  if there is a slayer on that tile. The effect of a vampire bite on a slayer is to decrease its lives by one.
 *  
 *  Vampire
Behaviour: Moves from right to left, if able to do so; bites any slayer on the adjacent left tile.
Resistance: 5 lives.
Damage: Each bite effects 1 point of damage on its target (removes one of its lives).
Frequency: At most one bite per cycle.
Speed: 1 tile every 2 cycles.
Graphics: Represented on the board by the ASCII text “VˆV”;
*/


/*La gestión de  cuántos  vampiros  quedan  por  salir,  cuántos  vampiros  están  en el tablero o si los vampiros  han llegado
 *   al final  se realizará desde la propia  clase Vampire utilizando variables y métodos estáticos. El Game accederá a esos métodos
 *    para mostrar la información o para terminar el juego cuando sea necesario
 */

public class Vampire {

	private static int resistance = 5, 
			frequency = 1, //in Level.java, it is a double type
			damage = 1;
	private static int vampLeftBoard, vampToPlace, vampMadeIt;
	
	private int life = 5;
	
	//speed= 0.5; //1 tile every 2 cycles
	
	private int x, y; //position coordinates on the board
	
	private static String representation =  "VˆV";
	
	/*Slayer y Vampire tienen un atributo en el que almacenan una referencia al juego, eso es, una instancia de la clase Game 
	 * (que será la única en el programa) que como veremos contiene la lógica del juego. De este modo, estas clases podrán usar
	 *  los métodos de la clase Game para consultar si pueden hacer o no una determinada acción.*/
	private Game currentGame;
	
	public Vampire (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//methods
	
	void bite() {
		
	}
}


