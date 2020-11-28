package logic.lists;

import logic.Game;
import logic.gameObjects.Slayer;

public class SlayerList {
	private int size; //Number of slayers on the board, not length of array slayers
	private Slayer[] slayers;
	
	
	//constructor 
	public SlayerList(int n) {	
		//slayers length (n) will be rows*columns, but size is initially 0, since there are no slayers on the board yet
		size = 0;
		slayers = new Slayer[n];
	}
		
	
	//returns toString of corresponding slayer in (x,y) only if there is one
	public String toString(int x, int y) {
		String object = "";
		if (isHere(x, y) != -1)
			object = slayers[isHere(x, y)].toString();
		return object;
	}
	
	
	//returns -1 if there is no slayer in (x, y), or returns its position in the array if there is one
	public int isHere(int x, int y) {
		boolean found = false;
		int i = 0;
		while (!found && i < size) {
			if (slayers[i].isHere(x, y))
				found = true;
			else
				i++;
		}
		if (!found)
			i = -1;
		
		return i;
	}

	
	//adds a slayer on (x, y)
	//only called when it can be added (meaning player affords it and tile is free)
	public void addSlayer(int x, int y, Game game) {
		slayers[size] = new Slayer(x, y, game);
		size++;
	}

	
	//looks for a slayer of the list that can be bitten by the vampire on (x, y), 
	//and if so, decreases the lives of that slayer by "damage"
	public void bite(int x, int y, int damage) {
		int pos = isHere(--x, y); //slayer that is bitten should be to the left of the vampire on (x, y)
		if (pos != -1) {
			slayers[pos].damage(damage);
		}
	}
	
	
	//in charge of making each slayer of the list attack
	public void attack(){
		for(int pos = 0; pos < size; pos++)
			slayers[pos].attack();
	}
	
	
	/*if any slayer has died, eliminates the reference to it, by moving all elements one position to the left, 
	 * on the array, until the dead slayer's position*/
	public void removeDeadObj() {
		for (int i = 0; i < size; i++) {
			if (slayers[i].getLife() <= 0) {
				for (int j = i; j < size - 1; j++) {
					slayers[j] = slayers[j + 1];
				}
				size -= 1;
			}
		}
	}

	
	//called when resetting game
	public void reset() {
		size = 0;
	}
	
	
	/*checks if coins of player are enough to buy a slayer
	returns the cost of a slayer if he can afford it, and -1 if not*/
	/*public int canAfford(int coins) {
		int cost = Slayer.getCost();
		if(cost > coins)
			cost = -1;
		
		return cost;
	}*/
	
}