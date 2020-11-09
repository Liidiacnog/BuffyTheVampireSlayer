package logic;

import logic.lists.*;


public class GameObjectBoard {
	
	private VampireList vamps;
	private SlayerList slayers;
	private int columns, rows;
	
	//constructor 
	public GameObjectBoard(int cols, int rows, int nrOfVamps) {
		columns = cols;
		this.rows = rows;
		vamps = new VampireList(nrOfVamps);
		slayers = new SlayerList(rows * cols);
	}

	
	//returns representation of the board as a String[][] (matrix of Strings)
	public String[][] toStringMatrixBoard() {
		String[][] str = new String[rows][columns];
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < columns; ++j)
				str[i][j] = objectOn(i, j);
		}
		return str;
	}
	
	
	//returns the String of the object on (x, y) if there is one
	public String objectOn(int x, int y){
		String object = "";
		if(!isFree(x, y))
			object += vamps.toString(x, y) + slayers.toString(x, y);//returns toString() of vamp OR slayer who is on (col, row)
				
		return object;
	}
	
	
	
	
	public void update() {
		vamps.moveVamps();
	}

	public boolean vampCanMove(int x, int y) {
		return isFree(--x, y);
	}
	
	public boolean isFree (int x, int y) {
		boolean valid = false;
		if (validCords(x, y) && slayers.isHere(x, y) == -1 && vamps.isHere(x, y) == -1)
				valid = true;

		return valid;
	}
	
	public boolean validCords(int col, int row) {
		return (row >= 0 && col >= 0 && col < columns && row < rows);
	}
	
	

	public int vampsLeft() {
		return vamps.getVampsLeft();
	}

	public int vampsOnBoard() {
		return vamps.onBoard();
	}

	public int canAfford(int coins) {
		return slayers.canAfford(coins);
	}
	
	public void addSlayer(int i, int j, Game game) {//we suppose it's only called when Player can afford it
		if(isFree(i, j))
			slayers.addSlayer(i, j, game);
	}
	
	public void addVampire(int x, int y, Game game) { //we suppose it's only called when we haven't reached max number of vampires yet
		if(isFree(x, y)) 
			vamps.addVamp(x, y, game);
	}
	
	public void attack() {
		slayers.attack();
		vamps.attack();
	}

	
	public void bite(int x, int y, int damage) {
		slayers.bite(x, y, damage);
	}
	
	public void shootBullet(int x, int y, int damage) {
		vamps.shootBullet(x, y, damage);
	}
	
	public void removeDeadObj() {
		slayers.removeDeadObj();
		vamps.removeDeadObj();
	}

	public void reset(int nrOfVamps) {
		vamps.reset(nrOfVamps);
		slayers.reset();
	}
	
	public boolean vampsWin() {
		return vamps.wins();
	}

}