package logic;
/*
 * Encapsula la lógica del juego. Contiene un GameObjectBoard al que delega la funcionalidad.
Esta clase debe ser quien lleve la puntuación y también guardar una referencia a la instancia de Player.
 representa la partida en curso. Durante la ejecución de la aplicación solo se creará un objeto de la clase Game
 */


public class Game {

	//fields
	private static Level lvl;
	private Long seed;
	private GameObjectBoard board;
	
	//constructor
	public Game(Long seed, Level lvl) {
		this.lvl = lvl;
		this.seed = seed;
	}
	
	
	void update() {//actualiza el estado de todos los elementos del juego
		
	}
	
	
	
	
}
