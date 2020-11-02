package logic.lists;

import logic.gameObjects.Vampire;

public class VampireList {
	private int size = Vampire.getVampsOnBoard(); //total number of vampires that are currently in the game
	private Vampire[] vamp;
		
	public VampireList(int n) { //created with length = numberOfVamps of that Level
		vamp = new Vampire[n];
		size = 0; //at first vampsOnBoard will be 0
		Vampire.setVampsLeft(n);
	}
	
	public void addVamp(int x, int y) {
		vamp[size] = new Vampire(x, y);
		size++;
		Vampire.setVampsOnBoard(size);
		Vampire.setVampsLeft(Vampire.getVampsLeft() - 1);
		
	}
	
	
	public int isHere(int x, int y) { //if not found, returns -1, if found, return position in array
		boolean found = false;
		int i = 0;
		while (!found && i < size) {
			if (vamp[i].isHere(x, y) && vamp[i].getLife() > 0)
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
		return Vampire.getVampsLeft();
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
	
	public int getLife(int i) {
		return vamp[i].getLife();
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
	
	public void beenHit(int pos, int harm) {
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
	
	public void removeDeadObj() {
		for (int i = 0; i < size; i++) {
			if (vamp[i].getLife() == 0) {
				for (int j = i; j < size - 1; j++) {
					vamp[j] = vamp[j + 1];
				}
				size -= 1;
			}
		}
	}
}


