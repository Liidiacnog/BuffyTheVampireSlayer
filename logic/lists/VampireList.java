package logic.lists;

import logic.Game;
import logic.gameObjects.Vampire;

public class VampireList {
	
	private int size; //total number of vampires that are currently in the game
	private Vampire[] vamp;
		
	//constructor 
	public VampireList(int n) { //created with length = numberOfVamps of that Level
		vamp = new Vampire[n];
		size = 0;
		Vampire.updateData(size, n);
	}

	
	//returns toString of corresponding vampire in (x,y) only if there is one
		public String toString(int x, int y) {
			String object = "";
			if (isHere(x, y) != -1)
				object = vamp[isHere(x, y)].toString();
			return object;
		}
	
	
	//returns -1 if there is no vamp in (x, y), or returns its position in the array if there is one
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
	
	
	//adds a vampire on (x, y)
	public void addVamp(int x, int y, Game game) {
		vamp[size] = new Vampire(x, y, game);
		size++;
	}

	
	//looks for a vampire of the list that can be hit by the bullet shot by a slayer on (x, y), 
	//and if so, decreases the lives of that vampire by "damage"
	public void shootBullet(int x, int y, int damage){
		int posVamp = target(x, y);
		if(posVamp != -1)
			vamp[posVamp].damage(damage);
	}
	
	
	/*checks all vampires in the list and gets the leftmost (to the right of the column x, from which the bullet on (x, y) 
	 * has been shot), as target 
	 * 	if there is none, returns -1 */
	public int target(int x, int y) {
		int target = -1, i = 0, vampDistance, minDistance = -1;
		while(i < size) { 
			vampDistance = vamp[i].target(x, y);//returns distance at which bullet is from him (minimum is 1 "tile" away)
			if((vampDistance != -1 && vampDistance < minDistance) || minDistance == -1) {
				minDistance = vampDistance;
				target = i;
			}
			 ++i;
		}
		if (minDistance == -1) {
			target = -1;
		}
				
		return target;
	}
	
	
	//in charge of making each vampire of the list attack
		public void attack() {
		for (int i = 0; i < size; i++) {
			vamp[i].attack();
		}
	}
	
	
	//tells each vampire to move, if they can
	public void moveVamps() {
		for (int i = 0; i < size; i++) {
			vamp[i].move();
		}
	}
	
	
	/*if any vampire has died, eliminates the reference to it, by moving all elements one position to the left, 
	 * on the array, until the dead vampire's position*/
	public void removeDeadObj() {
		for (int i = 0; i < size; i++) {
			if (vamp[i].getLife() <= 0) {
				for (int j = i; j < size - 1; j++) {
					vamp[j] = vamp[j + 1];
				}
				size -= 1;
			}
		}
	}

	
	//called when resetting game
	public void reset(int nrOfVamps) {
		size = 0;
		Vampire.updateData(size, nrOfVamps);
	}
	
	
	//true if vampires win because one of them has reached the end of the board
	public boolean wins() {
		boolean end = false;
		int i = 0;
		while (!end && i < size) {
			if (vamp[i].reachEnd())
				end = true;
			else
				i++;
		}
		return end;
	}
	
	
	//Getters

	public int getVampsLeft() {
		return Vampire.getVampsLeft();
	}

	public int onBoard() { //TODO change?
		return size;
	}
	

}
