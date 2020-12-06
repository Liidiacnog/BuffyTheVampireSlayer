package logic.gameObjects;


import logic.Game;


public class ExplosiveVampire extends Vampire{

	private static final String representation = "V*V";
	private static final String damage = ""; 
	
	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game);
		life = resistance;
	}
	
	public void attack() {
		/* On being destroyed (i.e. the moment its lives reach zero) it explodes causing damage to all neighbouring vampires (the same
		damage as caused by a bullet), including its diagonal neighbours. */
		if(life > 0) {
			IAttack other = game.getAttackableInPos(col - 1, row); 
			if (other != null) {
				other.receiveExplosiveVampireAttack();//TODO voy por aquí, hacer lo de las direcciones para facilitar implementar recibir este ataque?
			}
		}			
	}
	
	
}
