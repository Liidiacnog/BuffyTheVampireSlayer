package logic.gameObjects;

import logic.Game;

public class Vampire extends GameElement{

	protected static final int resistance = 5, frequency = 1, damage = 1; //protected because subclasses use them
	private static final String VampireRepresentation = "VˆV"; 
	private static final String VampireStringifyRep = "V";
	private boolean movedBefore; //to check whether it is its turn to move or not(they move each 2 cycles)
	private static boolean reachEnd = false;
	protected boolean stunned = false;
	/*how many vampires are on the board,
	 * vampires that can appear taking into consideration which ones have appeared already and which ones have died: */
	protected static int vampsOnBoard = 0, vampsLeft;  
	
	//constructor 
	public Vampire (int x, int y, Game game) {  
		super(x, y, game, VampireRepresentation, resistance, VampireStringifyRep);
		movedBefore = true;
		vampsOnBoard++; //new vampire is added to the board on x, y
		vampsLeft--; //one less vampire can be added to the board
	}
	
	//constructor defined for subclasses
	public Vampire (int x, int y, Game game, String representation, int resistance, String sRep) {  
		super(x, y, game, representation, resistance, sRep);
		movedBefore = true;
		vampsOnBoard++; //new vampire is added to the board on x, y
		vampsLeft--; //one less vampire can be added to the board
	}
	
	//overwrites stringify on GameElement to include cyclesToMove
	public String stringify() {
		int cyclesToMove = 0;
		if (stunned || movedBefore) cyclesToMove = 1;
		return super.stringify() + ";" + cyclesToMove;
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

	//hides isDead() from GameElement to keep count of VampsOnBoard
	public boolean isDead() {
		boolean dead = false;
		if (life <= 0) {
			dead = true;
			vampsOnBoard--;
		}
		return dead;
	}
	
	
	//effect of garlicPush, overwritten by those who have a special behaviour (Explosive Vampires, for example)
	@Override
	public void receiveGarlicPush() {
		int newX = col + 1, newY = row;
		if(newX == game.getBoardColumns()) //if is eliminated from board
			life = 0;
		else if (game.garlicPushEffect(newX, newY)) { //if newX, newY is empty
			col = newX;
			stun();
		}//if the tile to its right is not empty, garlic push doesn't affect it
	}

	
	public boolean receiveVampireExplosion(int harm) {
		damage(harm);
		return false;
	}

	
	//used to implement the garlicPush, by stunning vampires that should be stunned
	public void stun() {
		stunned = true;
	}
	
	
	//moves if it's its turn to do so and there is no one on the tile where it would be going
	public void move() {
		if(!stunned) {
			if (!movedBefore) {
				if(game.vampCanMove(col, row)) {
					col -= 1;
					if(col == -1) 
						reachEnd = true;
				}
			}
			movedBefore = !movedBefore;
		}else { //stunned == true currently, so in the next turn it has to move again
			stunned = false;
			movedBefore = false;
		}
	}
	
	
	//returns new y coordinate of vampire if it were able to move
	public static int moveY(int y) {
		return y;
	}
	
	//returns new x coordinate of vampire if it were able to move
		public static int moveX(int x) {
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
	
	public static boolean getReachEnd() {
		return reachEnd;
	}
	
}
