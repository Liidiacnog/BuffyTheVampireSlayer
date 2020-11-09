package logic.lists;

import logic.Game;
import logic.gameObjects.Vampire;

public class VampireList {
	
	private int size; //total number of vampires that are currently in the game
	private Vampire[] vamp;
		
	public VampireList(int n) { //created with length = numberOfVamps of that Level
		vamp = new Vampire[n];
		size = 0;
		Vampire.updateData(size, n);
	}

	public void moveVamps() {
		for (int i = 0; i < size; i++) {
			vamp[i].move();
		}
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
	
	//returns toString of corresponding vampire in (x,y) only if there is one
	public String toString(int x, int y) {
		String object = "";
		if (isHere(x, y) != -1)
			object = vamp[isHere(x, y)].toString();
		return object;
	}

	public int getVampsLeft() {
		return Vampire.getVampsLeft();
	}

	public int onBoard() { //TODO change?
		return size;
	}
	
	public void addVamp(int x, int y, Game game) {
		vamp[size] = new Vampire(x, y, game);
		size++;
	}

	public void attack() {
		for (int i = 0; i < size; i++) {
			vamp[i].attack();
		}
	}
	
	public void shootBullet(int x, int y, int damage){
		int posVamp = target(x, y);
		if(posVamp != -1)
			vamp[posVamp].beenHit(damage);
	}
	
	public int target(int x, int y) {
		int target = -1, i = 0, vampDistance, minDistance = -1;
		while(i < size) { //checks all vampires in the list and gets the leftmost on the row of the bullet, as target
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

	public void reset(int nrOfVamps) {
		size = 0;
		Vampire.updateData(size, nrOfVamps);
	}
	
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

}
