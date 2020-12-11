package logic.gameObjects;


import logic.Game;


public class ExplosiveVampire extends Vampire{

	private static final String representation = "V*V";
	private static final String damage = ""; 
	private boolean explode = true; //by default
	
	
	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game);
		life = resistance;
	}
	
	
	public String toString() {
		return representation + "[" + life + "]";
	}
	
	
	public boolean explode(int harm) {
		/* On being destroyed (i.e. the moment its lives reach zero) it explodes causing damage to all neighbouring vampires 
		 * (the same damage as caused by a bullet), including its diagonal neighbours. */
		if(explode) {
			IAttack other = null;
			for (int i = col - 1; i <= col + 1; i++)
				for (int j = row - 1; j <= row + 1; j++) {
					if (i != col || j != row) {
						other = game.getAttackableInPos(i, j);
						if (other != null)
							other.receiveVampireExplosion(harm);
					}
				}
		}
		return false;
	}
	
	
	public void garlicPushEffect() {
		int newX = col + 1, newY = row;
		if(newX == game.getBoardColumns()) { //if is eliminated from board
			life = 0;
			explode = false;
		}
		else if (game.garlicPushEffect(newX, newY)) //if newX, newY is empty
			col = newX;
	}
	
}