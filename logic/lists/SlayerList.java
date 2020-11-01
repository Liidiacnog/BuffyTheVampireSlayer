package logic.lists;

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
	
	public String toString(int i) {
		return slayers[i].toString();
	}

	public void addSlayer(int x, int y) {
		slayers[size] = new Slayer(x, y);
		size++;
	}
	
	public void beenBitten(int pos, int harm) {
		slayers[pos].beenBitten(harm);
	}
	
	public String representation(int pos) {
		return slayers[pos].toString(); //only called when there is always 1 slayer or more
	}
	
	
	//getters
	
	public int getSize() {
		return size;
	}
	
	public int getX(int pos) {
		return slayers[pos].getX();
	}
	
	public int getY(int pos) {
		return slayers[pos].getY();
	}
	
	public int getDamage(int pos) {
		return slayers[pos].getDamage();
	}
	
	public static int getCost() {
		return Slayer.getCost();
	}	
	
	
}
