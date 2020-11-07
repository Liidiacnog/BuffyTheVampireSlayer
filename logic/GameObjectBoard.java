package logic;

import logic.lists.SlayerList;
import logic.lists.VampireList;

public class GameObjectBoard {
	
	private Game game;
	private VampireList vamps;
	private SlayerList slayers;
	private int columns, rows;
	
	GameObjectBoard(Game game) {
		this.game = game;
		columns = game.getCols();
		rows = game.getRows();
		vamps = new VampireList(game.getVampsNumber());
		slayers = new SlayerList(rows * columns);
	}

	public void moveVamps() {
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
		boolean valid = false;
		if (row >= 0 && col >= 0 && col < columns && row < rows)
			valid = true;
		
		return valid;
	}
	
	public String objectOn(int x, int y) {
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
	
	public void addSlayer(int i, int j) {//we suppose it's only called when Player can afford it
		if(isFree(i, j))
			slayers.addSlayer(i, j, game);
	}
	
	public void addVampire(int x, int y) { //we suppose it's only called when we haven't reached max number of vampires yet
		if(vamps.isHere(x,  y) == -1) //no vampire in that position
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

	public void resetValues() {
		vamps.reset();
		slayers.reset();
	}
	
	public boolean vampsWin() {
		return vamps.wins();
	}

}