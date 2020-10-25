package logic.gameObjects;

import logic.Game;

/*Slayer. Added by the player on a specified tile of the board. Costs 50 coins. Does not move.  On each cycle, fires a 
 * silver bullet which travels from left to right and strikes the leftmost vampire in the same row as the slayer, if 
 * there is a vampire in that row. The effect of the impact of a silver bullet on a vampire is to decrease its lives by one.
 * 
 * 
Slayer
Behaviour: Fires silver bullets at vampires in the same row.
Cost: 50 coins.
Resistance: 3 lives.
Frequency: One shot per cycle.
Damage: Each shot effects 1 point of damage on its target (removes one of its lives).
Reach: Can only fire straight (along the row) and forwards (right to left).
Graphics: Represented on the board by the ASCII text “<->”.
*/




public class Slayer {

	private static int cost = 50, resistance = 3, frequency = 1, damage = 1;
	private static String representation =  "<->";
	
	private int life;
	
	private int x, y; //position coordinates on the board
	
	
	
	/*Slayer y Vampire tienen un atributo en el que almacenan una referencia al juego, eso es, una instancia de la clase Game 
	 * (que será la única en el programa) que como veremos contiene la lógica del juego. De este modo, estas clases podrán usar
	 *  los métodos de la clase Game para consultar si pueden hacer o no una determinada acción.*/
	private Game currentGame;
	
	public Slayer(int x, int y) {
		this.x = x;
		this.y = y;
		life = resistance; //just initially
	}
	
	//methods
	
			//affect others
	
	public void draw() {
		System.out.print(" " + representation + "[" + life + "]");
	}

	public boolean isHere(int i, int j) {
		boolean found = false;
		if (x == i && y == j) {
			found = true;
		}
		return found;
	}

	public void fire() {
		//TODO game has to ask board, who has to tell lists, who have to tell vampires to say if there is one close enough to get hit
	}	
		
			//own state
		
	public void beenBitten() {
		life -= Vampire.getDamage();
	}
	
	

				//getters
	
	public static int getDamage() {
		return damage;
	}
	
	
}
	
	
