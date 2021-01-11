package logic.gameObjects;


import logic.Game;


public class Dracula extends Vampire {
	
	private static final String representation = "V-V"; 
	private static final String DraculaStringifyRep = "D"; //has a different name so that it doesn't hide the superclass attribute
	private static final int damage = Integer.MAX_VALUE; //Dracula always kills with one blow
	
	public Dracula(int x, int y, Game game) {
		super(x, y, game, representation, Vampire.resistance, DraculaStringifyRep);
	}
	
	
	public void attack() {
		if(life > 0) {
			IAttack other = game.getAttackableInPos(col - 1, row); 
			if (other != null) {
				other.receiveDraculaAttack();
			}
		}	
	}
	
	
	//hides isDead() of Vampire because we need to change 'appeared' so that Dracula can appear again afterwards
	public boolean isDead() {
		boolean dead = false;
		if (life <= 0) {
			dead = true;
			vampsOnBoard--;
			game.draculaDie();
		}
		return dead;
	}
	
	@Override
	public void damage(int harm) {
		life -= harm;
		if (life <= 0)
			game.draculaDie();
	}
	
	
	//affects all vampires except Dracula
	@Override
	public void receiveLightFlash() {}

	
}
