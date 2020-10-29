package logic.lists;

import logic.gameObjects.Vampire;

public class VampireList {
	private int size = Vampire.getVampsOnBoard(); //total number of vampires that are currently in the game
	private Vampire[] vamp;
		
	public VampireList(int n) { //created with length = numberOfVamps of that Level
		vamp = new Vampire[n];
		size = 0; //at first vampsOnBoard will be 0
	}
	
	public void addVamp(int x, int y) {
		vamp[Vampire.getVampsOnBoard()] = new Vampire(x, y);
		int newNrOfVamps = Vampire.getVampsOnBoard() + 1;
		Vampire.setVampsOnBoard(newNrOfVamps);
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
		return vamp.length - size;
	}
	
	public int onBoard() {
		int sum = 0;
		for (int i = 0; i < size; i++) {
			if (vamp[i].life() > 0) {
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

	public boolean wins() {
		boolean end = false;
		int i = 0;
		while (!end && i < size) {
			if (vamp[i].reachEnd()) {
				end = true;
			}
			i++;
		}
		return end;
	}
}


