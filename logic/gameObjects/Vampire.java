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

The Game object will access these attributes/methods to display information or to terminate the game when necessary.
 */

public class Vampire {

	
	private int life = 5;
	private boolean placed;
	private static int frequency = 1, damage = 1;
	//how many vampires are on the board, total number of vampires that can appear in the game:
	private static int  vampsOnBoard = 0, vampsThisLevel, vampsLeft;  
<<<<<<< HEAD
	private int col, row; //position coordinates on the board
=======
	private int x, y; //position coordinates on the board
>>>>>>> branch 'main' of https://github.com/Liidiacnog/Test.git
	private boolean movedBefore; //check whether it is its turn to move or not(they move each 2 cycles)
	private static String representation = "VˆV";
	
	
	private Game currentGame; //Game game needs to be passed via a parameter whenever needed
	
	//constructor 
	
	public Vampire (int x, int y) {  
		row = x;
		col = y;
		placed = true;
		movedBefore = true;
	}
	
	//methods
	
		//affect others
	

		//own state
	
	public boolean reachEnd() {
		return col == 0; //column == first
	}
	
	public void move() {
		if (!movedBefore)
<<<<<<< HEAD
			--col;//vamp move to the left only
=======
			--x;//vamp move to the left only
>>>>>>> branch 'main' of https://github.com/Liidiacnog/Test.git
		movedBefore = !movedBefore;
	}
	
	public String toString() {
		return representation + "[" + life + "]";
	}
	
	public boolean isHere(int i, int j) {
		boolean found = false;
		if (row == i && col == j) {
			found = true;
		}
		return found;
	}
	
	public void beenHit(int harm) {
		life -= harm;
	}
	
	public void beenKilled() { 
		vampsOnBoard--;
		life = 0;
	}
	
	
	//setters
	
	public void setvampsThisLevel(int nrOfVamps) {
		vampsThisLevel = nrOfVamps;
	}

	public static void setVampsLeft(int nr) {
		vampsLeft = nr;
	}
	
	public static void setVampsOnBoard(int nr) {
		vampsOnBoard = nr;
	}
	
<<<<<<< HEAD

	//getters
	public boolean getMoved() {
		return movedBefore;
	}
=======
	//getters
>>>>>>> branch 'main' of https://github.com/Liidiacnog/Test.git
	
	public int getLife() {
		return life;
	}
	
	public static int getDamage() {
		return damage;
	}
	
	public int getX() {
		return row;
	}
	
	public int getY() {
		return col;
	}
	
	public static int getVampsOnBoard() {
		return vampsOnBoard;
	}
<<<<<<< HEAD
=======
	
	public static int getVampsLeft() {
		return vampsLeft;
	}

	public int life() {
		return life;
	}

	public boolean reachEnd() {
		return x == 0;
	}
>>>>>>> branch 'main' of https://github.com/Liidiacnog/Test.git
	
	public static int getVampsLeft() {
		return vampsLeft;
	}

}


