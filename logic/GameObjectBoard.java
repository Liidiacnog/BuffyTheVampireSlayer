package logic;

import java.util.ArrayList;
import logic.gameObjects.GameElement;
import logic.gameObjects.IAttack;
import logic.gameObjects.Vampire;
import logic.gameObjects.Slayer;


/*The classes Game and Board only deal with generic elements (i.e. of class GameElement) and so cannot distinguish 
the concrete class of the objects being manipulated.*/
public class GameObjectBoard {
	
	private ArrayList<GameElement> gameElements;
	private int columns, rows;
	
	//constructor 
	public GameObjectBoard(int cols, int rows, int nrOfVamps) {
		columns = cols;
		this.rows = rows;
		gameElements = new ArrayList<>();
	}



	public String objToString(int x, int y) {
		int pos;
		String str = "";
		pos = indexOf(x, y);
		if (pos != -1)
			str = gameElements.get(pos).toString();
		return str;
	}

	//acts like ArrayList indexOf(), except it takes advantage of the fact that on each tile of the board
	//there can only be one element, so each position coordinates are univocally linked to the element on that position
	private int indexOf(int x, int y) {
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
	
	//actions in game loop:
	
	//moves elements that should do so
	public void update() {
		for(int i = 0; i < gameElements.size(); i++) {
			gameElements.get(i).move();
		}
	}

	
	//tells lists to tell their elements to attack
	public void attack() {
		for(int i = 0; i < gameElements.size(); i++) {
			gameElements.get(i).attack(columns);
		}
	}

	
	//if (x, y) is not occupied, adds a vampire on it
	public void addVampire(int x, int y, Game game) { 
		//we assume it's only called when we haven't reached max number of vampires yet
		if(isFree(x, y)) {
			gameElements.add(new Vampire(x, y, game));
		}
	}
	
	
	//if (i, j) is not occupied, adds a slayer on it
	public void addSlayer(int i, int j, Game game) {
		//we assume it's only called when player can afford it
		if(isFree(i, j)) {
			gameElements.add(new Slayer(i, j, game));
		}
	}
	
	
	//true if the vampire on (x, y) could move	
	public boolean vampCanMove(int x, int y) {
		int newX = Vampire.canMoveX(x);
		int newY = Vampire.canMoveY(y);
		return isFree(newX, newY) || gameElements.get(indexOf(x, y)).reachEnd(newX, newY);
	}
	
	
	//true if (x, y) is a is a valid position to put an object, and that "tile" isn't occupied by any object of the board 
	public boolean isFree (int x, int y) {
		boolean valid = false;
		if (validCords(x, y) && indexOf(x, y) == -1)
				valid = true;
		return valid;
	}
	
	
	//true if (col, row) are valid coordinates on the current board
	public boolean validCords(int col, int row) {
		return (row >= 0 && col >= 0 && col < columns && row < rows);
	}
	
	
	//calls methods in lists in charge of removing dead objects
	public void removeDeadObj() {
		for(int i = 0; i < gameElements.size(); i++) {
			if(gameElements.get(i).isDead())
				gameElements.remove(gameElements.get(i));
		}
	}

	
	//resets elements on the board
	public void reset(int nrOfVamps) {
		gameElements.clear();
		Vampire.updateData(0, nrOfVamps);
	}
	
	
	//true if a vampire has reached the end of the board
	public boolean vampsWin() {
		boolean win = false;
		for(int i = 0; i < gameElements.size(); ++i) {
			if(gameElements.get(i).reachEnd())
				win = true;
		}
		return win;
	}

	
	//returns the cost of a slayer if coins are enough to afford it, otherwise returns -1
	public int canAfford(int coins) {
		return Slayer.canAfford(coins);
	}
	
	public boolean checkEnd() {
		return getVampsLeft() == 0 && getVampsOnBoard() == 0;
	}

	public IAttack getAttackable(int i, int j) {
		IAttack objective = null;
		if (indexOf(i, j) != -1)
			objective = gameElements.get(indexOf(i, j));
		return objective;
	}
	
	//Getters:
	
	public int getVampsLeft() {
		return Vampire.getVampsLeft();
	}

	public int getVampsOnBoard() {
		return Vampire.getVampsOnBoard();
	}


}
