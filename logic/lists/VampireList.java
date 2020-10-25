package logic.lists;

import logic.Game;
import logic.gameObjects.Vampire;

/*
 * VampireList, SlayerList: : These classes encapsulate the state and the behaviour of the game elements. Their state — position on the board, 
 * lives left, etc. — is contained in private attributes. They also have an attribute in which they store (a reference to) the game, i.e. 
 * an instance of the class Game, in order to be able to invoke the methods of this instance to consult as to whether or not they can perform a
 *  given action. Note that there will only ever be one instance of the Game class in the program, i.e. the one created in the main method, which 
 *  we will refer to as the game object.
 */


public class VampireList {
	private int size; //total number of vampires that are currently in the game
	private Vampire[] vamp;
	
	Game currentGame;
	
	public VampireList(int n) {
		size = n;
		vamp = new Vampire[size];
		for (int i = 0; i < vamp.length; i++) {
			vamp[i] = new Vampire(0, 0, currentGame.level); //TODO why on x = 0, and y = 0?
			/*Appears in a randomly-chosen row of the rightmost column of the board. Moves one tile to the left every two turns,
			 *  as long as the destination tile of the move is free. On each turn, it bites the slayer in the next tile to the left, 
			 *  if there is a slayer on that tile. The effect of a vampire bite on a slayer is to descrease its lives by one*/
		}
	}
	
	public int isHere(int x, int y) {
		boolean found = false;
		int i = 0;
		while (!found && i < size) {
			if (vamp[i].isHere(x, y))
				found = true;
			else
				i++;
		}
		if (!found) {
			i = -1;
		}
		
		return i;
	}
	
	public void draw(int i) {
		vamp[i].draw();
	}
	
	public int left() {
		int sum = 0;
		for (int i = 0; i < vamp.length; i++) {
			if (!vamp[i].getPlaced()) {
				sum++;
			}
		}
		
		return sum;
	}
	
	public int onBoard() {
		int sum = 0;
		for (int i = 0; i < vamp.length; i++) {
			if (vamp[i].getPlaced()) {
				sum++;
			}
		}
		
		return sum;
	}
	
}
