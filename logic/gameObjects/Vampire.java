package logic.gameObjects;

import logic.Game;

public class Vampire extends GameElement{

	protected static final int resistance = 5, frequency = 1, damage = 1; //protected because subclasses use them
	private boolean movedBefore; //to check whether it is its turn to move or not(they move each 2 cycles)
	private final String representation = "VˆV";
	private static boolean reachEnd = false;
	/*how many vampires are on the board,
	 * vampires that can appear taking into consideration which ones have appeared already and which ones have died:
	 */
	private static int  vampsOnBoard = 0, vampsLeft;  
	
	//constructor 
	public Vampire (int x, int y, Game game) {  
		super(x, y, game);
		life = resistance;
		movedBefore = true;
		vampsOnBoard++; //new vampire is added to the board on x, y
		vampsLeft--; //one less vampire can be added to the board
	}
	

	public String toString() {
		return representation + "[" + life + "]";
	}

	
	//calls method in game which will check if any slayer can be bitten from (col, row)
	public void attack() {
		if(life > 0) {
			IAttack other = game.getAttackableInPos(col - 1, row); 
			if (other != null) {
				other.receiveVampireAttack(damage);
			}
		}		
	}
		
	
	//reduces its life by "harm"	
	public void damage(int harm) {
		life -= harm;
		if (life <= 0)
			explode(harm);
	}
			
	public boolean receiveVampireExplotion(int harm) {
		damage(harm);
		return false;
	}
	
	// If vamp is not explosive, method explode() does nothing
	protected boolean explode(int harm) {
		return false;
	}

	//moves if it's its turn to do so, and there is no one on the tile where he should be going
	public void move() {
		if (game.vampCanMove(col, row)) {
			if (!movedBefore) {
				col -= 1;
			}
			movedBefore = !movedBefore;
		}
	}
	
	//returns new y coordinate of vampire if it were able to move
	public static int canMoveY(int y) {
		return y;
	}
	
	//returns new x coordinate of vampire if it were able to move
		public static int canMoveX(int x) {
			return x - 1;
	}
	
		
	//returns distance at which the bullet is from him (the minimum is 1 "tile" away), or returns -1 if he can't get hit by it
	public int target(int xBullet, int yBullet) {
		int dist = -1;
		if(row == yBullet && xBullet < col)
			dist = col - xBullet;
		
		return dist;
	}
	
		
	//updates vampsOnBoard, vampsThisLevel, vampsLeft ONLY when a list containing them is created or reset  
	public static void updateData(int nr, int vampsLevel) {
		vampsOnBoard = nr;
		vampsLeft = vampsLevel - nr;
	}
	
	public boolean receiveSlayerAttack(int harm) {
		damage(harm);
		return true;
	}
	
	
	//Getters

	public static int getVampsLeft() {
		return vampsLeft;
	}

	public static int getVampsOnBoard() {
		return vampsOnBoard;
	}
	
	public int getLife() {
		return life;
	}
	
	public static boolean getReachEnd() {
		return reachEnd;
	}
	
	public static void setReachEnd(boolean b) {
		reachEnd = b;
	}
}
