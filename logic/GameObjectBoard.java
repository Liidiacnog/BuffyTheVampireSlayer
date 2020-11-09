package logic;

import logic.lists.*;


public class GameObjectBoard {
	
	private VampireList vamps;
	private SlayerList slayers;
	private int columns, rows;
	
	public GameObjectBoard(int cols, int rows, int nrOfVamps) {
		columns = cols;
		this.rows = rows;
		vamps = new VampireList(nrOfVamps);
		slayers = new SlayerList(rows * cols);
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
	
	public String encodeGame(int x, int y) {
		return "" + vamps.toString(x, y) + slayers.toString(x, y);
	}

	public int vampsLeft() {
		return vamps.getVampsLeft();
	}

	public int vampsOnBoard() {
		return vamps.onBoard();
	}

	public int getCostSlayers() {
		return slayers.getCost();
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