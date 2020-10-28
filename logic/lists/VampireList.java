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
	private int size = Vampire.getVampRemaining(); //total number of vampires that are currently in the game
	private Vampire[] vamp;
	
	Game currentGame;
	
	public VampireList(int n) { //created empty, at first (vampRemaining will be 0)
		vamp = new Vampire[n];
		size = 0;
	}
	
	public void addVamp(int x, int y) {
		vamp[Vampire.getVampRemaining()] = new Vampire(x, y);
		int newNrOfVamps = Vampire.getVampRemaining() + 1;
		Vampire.setVampsRemaining(newNrOfVamps);
	}
	
	
	public int isHere(int x, int y) { //if not found, returns -1, if found, return position in array
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
	
	public String toString(int i) {
		return vamp[i].toString();
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
	
	public void moveVamps(int i) {
			vamp[i].move();
	}
	
	public int getSize() {
		return size;
	}
	
	public int getX(int position) {
		return vamp[position].getX();
	}
	
	public int getY(int position) {
		return vamp[position].getY();
	}
	
	public void getHit(int pos, int harm) {
		vamp[pos].beenHit(harm);
	}
	
	
	public int getDamage(int pos) {
		return vamp[pos].getDamage();
	}
}
