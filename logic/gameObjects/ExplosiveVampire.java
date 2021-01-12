package logic.gameObjects;


import logic.Game;


public class ExplosiveVampire extends Vampire{

	private static final String ExpVampStringifyRep = "EV"; //has a different name so that it doesn't hide the superclass attribute
	private static final String ExpVampRepresentation = "V*V";
	private static final int damage = 1; 
	private boolean explode; //used to distinguish between being killed by a slayer (true) and being dropped out of the board (false)
	
	
	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game, ExpVampRepresentation, Vampire.resistance, ExpVampStringifyRep);
		explode = true;
	}
	
	
	public boolean explode() {
		/* On being destroyed (i.e. the moment its lives reach zero) it explodes causing damage to all neighbouring vampires 
		 * (the same damage as caused by a bullet), including its diagonal neighbours; unless it has been killed by being 
		 * eliminated from board by garlic push (then explode will be false) */
		if(explode) {
			IAttack other = null;
			for (int i = col - 1; i <= col + 1; i++)
				for (int j = row - 1; j <= row + 1; j++) {
					if (i != col || j != row) {
						other = game.getAttackableInPos(i, j);
						if (other != null)
							other.receiveVampireExplosion(damage);
					}
				}
		}
		return false;
	}
	
	//reduces its life by "harm". If life <= 0, it explodes
	@Override
	public void damage(int harm) {
		life -= harm;
		if (life <= 0)
			explode();
	}
	
	@Override
	public void receiveGarlicPush() {
		int newX = col + 1, newY = row;
		if(newX == game.getBoardColumns()) { //if is eliminated from board
			life = 0;
			explode = false;
		}
		else if (game.garlicPushEffect(newX, newY)) { //if newX, newY is empty
			col = newX;
			resetVampMovedBefore();
		}//if the tile to its right is not empty, garlic push doesn't affect it
	}
	
	@Override
	public void receiveLightFlash() {
		life = 0;	
		explode = false;
	}
	
	
}