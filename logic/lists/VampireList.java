package logic.lists;

import logic.gameObjects.Vampire;

/*
 * VampireList, SlayerList: Contienen arrays de los respectivos elementos del juego, así como métodos auxiliares para su gestión. 
 * En esta práctica usaremos obligatoriamente arrays convencionales, en las siguientes prácticas las substituiremos por ArrayLists.

 */


public class VampireList {
	private int size;
	private Vampire[] vamp;
	
	public VampireList(int n) {
		size = n;
		vamp = new Vampire[size];
		for (int i = 0; i < vamp.length; i++) {
			vamp[i] = new Vampire(0, 0);
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
