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


/*The class Vampire itself is responsible for managing
◦	the total number of vampires that can appear in the game,
◦	how many vampires are on the board,
◦	whether or not the vampires have reached the l.h.s. of the board,
using static attributes or methods. 

The ame object will access these attributes/methods to display information or to terminate the game when necessary.
 */

public class Vampire {

	private static int frequency = 1, damage = 1;
	//how many vampires are on the board, total number of vampires that can appear in the game
	private static int vampLeftBoard, vampThisLevel; 
	
	private int life = 5;
	
	//speed= 0.5; //1 tile every 2 cycles
	
	private int x, y; //position coordinates on the board
	private boolean placed;
	private boolean moveBefore; //check whether it is its turn to move or not(they move each 2 cycles)
	
	private static String representation = "VˆV";
	
	/* Slayer y Vampire tienen un atributo en el que almacenan una referencia al juego, eso es, una instancia de la clase Game 
	 * (que será la única en el programa) que como veremos contiene la lógica del juego. De este modo, estas clases podrán usar
	 * los métodos de la clase Game para consultar si pueden hacer o no una determinada acción.*/
	private Game currentGame;
	
	//constructor 
	
	public Vampire (int x, int y, int lvlVamps) {
		this.x = x;
		this.y = y;
		placed = false;
		moveBefore = true;
		vampLeftBoard = 0;
		vampThisLevel = lvlVamps;
	}
	
	//methods
	
		//affect others
	
	public void bite(Game currentGame) {
		//game has to ask board, who has to tell lists, who have to tell slayers to say if there is one close enough to be bitten
	}
	
		//own state
	
	public void draw() {
		System.out.print(" " + representation + "[" + life + "]");
	}
	
	public boolean isHere(int i, int j) {
		boolean found = false;
		if (x == i && y == j && placed) {
			found = true;
		}
		return found;
	}
	
	public void beenHit() {
		life -= Slayer.getDamage();
	}

			//getters

	
	public static int getDamage() {
		return damage;
	}
	
	public boolean getPlaced() {
		return placed;
	}
}


