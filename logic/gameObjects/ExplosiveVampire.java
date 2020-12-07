package logic.gameObjects;


import logic.Game;


public class ExplosiveVampire extends Vampire{

	private static final String representation = "V*V";
	private static final String damage = ""; 
	
	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game);
		life = resistance;
	}
	
	public boolean explode(int harm) {
		/* On being destroyed (i.e. the moment its lives reach zero) it explodes causing damage to all neighbouring vampires (the same
		damage as caused by a bullet), including its diagonal neighbours. */
		IAttack other = null;
		for (int i = col - 1; i <= col + 1; i++) {
			for (int j = row - 1; j <= row + 1; j++) {
				if (i != col || j != row) {
					other = game.getAttackableInPos(i, j);
					if (other != null) {
						other.receiveVampireExplotion(harm);
					}
					
				}
			}
		}
		
		return false;
	}
	
}

//TODO voy por aquí, hacer lo de las direcciones para facilitar implementar recibir este ataque?