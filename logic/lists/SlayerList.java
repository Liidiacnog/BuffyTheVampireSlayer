package logic.lists;

import logic.Game;
import logic.gameObjects.Slayer;

public class SlayerList {
	private int size; //Number of slayers on the Board, not length of array slayers
	private Slayer[] slayers;
	
	public SlayerList(int n) {	
		//slayers length (n) will be rows*columns, but size is initially 0, since there are no slayers on the board yet
		size = 0;
		slayers = new Slayer[n];
	}
	
	public int isHere(int x, int y) {
		boolean found = false;
		int i = 0;
		while (!found && i < size) {
			if (slayers[i].isHere(x, y))
				found = true;
			else
				i++;
		}
		if (!found) {
			i = -1;
		}
		
		return i;
	}

	public String toString(int x, int y) {
		String object = "";
		if (isHere(x, y) != -1)
			object = slayers[isHere(x, y)].toString();
		return object;
	}

	public int getCost() {
		return Slayer.getCost();
	}
	
	public void addSlayer(int x, int y, Game game) {
		slayers[size] = new Slayer(x, y, game);
		size++;
	}

	public void bite(int x, int y, int damage) {
		x--;
		int pos = isHere(x, y);
		if (pos != -1) {
			slayers[pos].damage(damage);
		}
	}
	
	public void attack(){
		for(int pos = 0; pos < size; pos++)
			slayers[pos].attack();
	}
	
	public void removeDeadObj() {
		for (int i = 0; i < size; i++) {
			if (slayers[i].getLife() == 0) {
				for (int j = i; j < size - 1; j++) {
					slayers[j] = slayers[j + 1];
				}
				size -= 1;
			}
		}
	}

	public void reset() {
		size = 0;
	}
}