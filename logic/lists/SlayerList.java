package logic.lists;

import logic.gameObjects.Slayer;

/*
 * VampireList, SlayerList: Contienen arrays de los respectivos elementos del juego, así como métodos auxiliares para su gestión. 
 * En esta práctica usaremos obligatoriamente arrays convencionales, en las siguientes prácticas las substituiremos por ArrayLists.
 */
 
public class SlayerList {
	private int size; //Number of slayers
	private Slayer[] slayers;
	
	public SlayerList(int n) {
		slayers = new Slayer[n]; // n is the maximum number of possible slayers given a board
		size = 0;
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
	
	public void draw(int i) {
		slayers[i].draw();
	}

	public void addSlayer(int x, int y) {
		slayers[size] = new Slayer(x, y);
		size++;
	}
}
