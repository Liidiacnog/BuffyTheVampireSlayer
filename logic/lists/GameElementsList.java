package logic.lists;

import java.util.ArrayList;
import logic.gameObjects.GameElement;
import logic.gameObjects.IAttack;

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
			gameElements.get(i).update();
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
		if(gameElements.size() > 0)
			for(int i = gameElements.size() - 1; i >= 0; i--) {
				//We need to remove them starting from the end, to the beginning of the ArrayList, to avoid index errors
				if(gameElements.get(i).isDead()) 
					gameElements.remove(gameElements.get(i));
			}
	}

	
	public void clear() {
		gameElements.clear();
	}
	
	
	public IAttack getAttackable(int i, int j) {
		IAttack objective = null;
		if (indexOf(i, j) != -1)
			objective = gameElements.get(indexOf(i, j));
		return objective;
	}
	
	
	public void receiveGarlicPush() {
		for (int i = 0; i < gameElements.size(); i++) {
			gameElements.get(i).receiveGarlicPush();
		}		
	}

	
	public void receiveLightFlash() {
		for (int i = 0; i < gameElements.size(); i++)
			gameElements.get(i).receiveLightFlash();
	}

	
	//returns stringify data of each GameElement in a single String, by calling stringify() on each GameElement
	public String stringify() {
		String str = "";
		for (int i = 0; i < gameElements.size(); i++)
			str += gameElements.get(i).stringify() + '\n';
		return str;
	}
	
	
	
	
}
