package logic;


import logic.gameObjects.*;
import logic.lists.*;


public class GameObjectBoard {
	
	private GameElementsList gameElements;
	private int columns, rows;
	
	//constructor 
	public GameObjectBoard(int cols, int rows, int nrOfVamps) {
		columns = cols;
		this.rows = rows;
		gameElements = new GameElementsList();
	}


	//returns String of object in position (x, y), if there is one
	public String objToString(int x, int y) {
		int pos;
		String str = "";
		pos = gameElements.indexOf(x, y);
		if (pos != -1)
			str = gameElements.toStringElement(pos);
		return str;
	}

	
	
	//actions in game loop:
	
	//moves elements that should do so
	public void update() {
		gameElements.update();
	}

	
	//tells lists to tell their elements to attack
	public void attack() {
		gameElements.attack();
	}

	
	//if (x, y) is not occupied, adds a vampire on it
	public boolean addVampire(int x, int y, Game game) { 
		boolean added = false;
		//we assume it's only called when we haven't reached max number of vampires yet
		if(isFree(x, y)) {
			gameElements.add(new Vampire(x, y, game));
			added = true;
		}
		return added;
	}
	
	
	//if (x, y) is not occupied, adds Dracula on it 
	public boolean addDracula(int x, int y, Game game) { 
		boolean added = false;
		//we assume it's only called when we haven't added Dracula yet
		if(isFree(x, y)) {
			gameElements.add(new Dracula(x, y, game));
			added = true;
		}
		return added;
	}
	
	
	//if (x, y) is not occupied, adds a vampire on it
	public boolean addExplosiveVampire(int x, int y, Game game) { 
		boolean added = false;
		//we assume it's only called when we haven't reached max number of vampires yet
		if(isFree(x, y)) {
			gameElements.add(new ExplosiveVampire(x, y, game));
			added = true;
		}
		return added;
	}
	
	
	//if (i, j) is not occupied, adds a slayer on it
	public void addSlayer(int i, int j, Game game) {
		//we assume it's only called when player can afford it
		gameElements.add(new Slayer(i, j, game));
	}

	//adds corresponding BloodBank, only called if it can be added on x, y
	public void addBloodBank(int x, int y, int cost, Game game) {
		gameElements.add(new BloodBank(x, y, cost, game));
	}
	
	
	//true if the vampire on (x, y) could move	(if newX, newY contains no other GameElement)
	public boolean vampCanMove(int x, int y) {
		int newX = Vampire.moveX(x);
		int newY = Vampire.moveY(y);
		return gameElements.indexOf(newX, newY) == -1;
	}
	
	
	//true if (x, y) is a is a valid position to put an object, and that "tile" isn't occupied by any object of the board 
	public boolean isFree (int x, int y) {
		boolean valid = false;
		if (validCords(x, y) && gameElements.indexOf(x, y) == -1)
				valid = true;
		return valid;
	}
	
	
	//true if (col, row) are valid coordinates on the current board
	public boolean validCords(int col, int row) {
		return (row >= 0 && col >= 0 && col < columns && row < rows);
	}
	
	
	//calls methods in lists in charge of removing dead objects
	public void removeDeadObj() {
		gameElements.removeDeadObj();
	}

	
	//resets elements on the board
	public void reset(int nrOfVamps) {
		gameElements.clear();
		Vampire.updateData(0, nrOfVamps);
	}
	
	
	//true if a vampire has reached the end of the board
	public boolean vampsWin() {
		return Vampire.getReachEnd();
	}
	
	
	//true if the player has finished the game by eliminating all possible vampires
	public boolean checkEnd() {
		return Vampire.getVampsLeft() == 0 && Vampire.getVampsOnBoard() == 0;
	}

	
	public IAttack getAttackable(int i, int j) {
		return gameElements.getAttackable(i, j);
	}
	
	
	public void receiveGarlicPush() {
		gameElements.receiveGarlicPush();		
	}


	public void receiveLightFlash() {
		gameElements.receiveLightFlash();		
	}

	public String stringify() {
		return gameElements.stringify();
	}

	
	//Getters:

	public int getColumns() {
		return columns;
	}

}
