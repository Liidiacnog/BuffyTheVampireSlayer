package logic.lists;

import logic.gameObjects.Slayer;
import logic.gameObjects.Vampire;

/*
 * VampireList, SlayerList: Contienen arrays de los respectivos elementos del juego, así como métodos auxiliares para su gestión. 
 * En esta práctica usaremos obligatoriamente arrays convencionales, en las siguientes prácticas las substituiremos por ArrayLists.
 */
 
public class SlayerList {
	private int size; //Number of slayers
	private Slayer[] slayers;
	
	public SlayerList(int n) {
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
		int newNrOfVamps = Vampire.getVampRemaining() + 1;
		Vampire.setVampsRemaining(newNrOfVamps);
		
		slayers[size] = new Slayer(x, y);
		size++;
	}
	
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
	
	public void getBitten(int pos, int harm) { //TODO change name to sth that doesn't require "get"
		slayers[pos].beenBitten(harm);
	}
	
}
