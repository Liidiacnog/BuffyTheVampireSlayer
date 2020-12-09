package logic.lists;

import java.util.ArrayList;
import logic.gameObjects.GameElement;
import logic.gameObjects.IAttack;
import logic.gameObjects.Slayer;
import logic.gameObjects.Vampire;

public class GameElementsList {

	private ArrayList<GameElement> gameElements;
	
	public GameElementsList() {
		gameElements = new ArrayList<>();
	}
	
	//returns String corresponding to the element on position pos in the ArrayList
	public String toStringElement(int pos) {
		return gameElements.get(pos).toString();
	}
	
	
	//acts like ArrayList indexOf(), except it takes advantage of the fact that on each tile of the board
	//there can only be one element, so each position coordinates are univocally linked to the element on that position
	public int indexOf(int x, int y) {
		boolean found = false;
		int i = 0, pos = -1;
		while (i < gameElements.size() && !found) {
			if(gameElements.get(i).isHere(x, y)) {
				found = true;
				pos = i;
			}
			else
				++i;
		}
		
		return pos;
	}
	
	
	public void update() {
		for(int i = 0; i < gameElements.size(); i++) {
			gameElements.get(i).move();
		}
	}
	
	public void attack() {
		for(int i = 0; i < gameElements.size(); i++) {
			gameElements.get(i).attack();
		}
	}
	
	public void add(GameElement el) {
		gameElements.add(el);
	}
	
	
	public void removeDeadObj() {
		for(int i = 0; i < gameElements.size(); i++) {
			if(gameElements.get(i).isDead())
				gameElements.remove(gameElements.get(i));
		}
	}
	
	
	public void clear() {
		gameElements.clear();
	}
	
	
	//true if a vampire has reached the end of the board
	//(doesn't give problems bc only vampires implement reachEnd(), slayers always return false)
	//TODO maybe will give problems if more elements are added and they reachEnd in a different way?
	/*public boolean vampsWin() {
		boolean win = false;
		for(int i = 0; i < gameElements.size(); ++i) {
			if(gameElements.get(i).reachEnd())
				win = true;
		}
		return win;
	}*/
	
	
	public IAttack getAttackable(int i, int j) {
		IAttack objective = null;
		if (indexOf(i, j) != -1)
			objective = gameElements.get(indexOf(i, j));
		return objective;
	}

	public int getBloodBankCoins() {
		int coins = 0;
		for (int i = 0; i < gameElements.size(); i++) {
			if (gameElements.get(i).getBloodBankCoins() != -1)
				coins = gameElements.get(i).getBloodBankCoins();
		}
		return coins;
	}
	
	
	
	
}
