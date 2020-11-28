package logic.gameObjects;

public interface IAttack {

	void attack(int columns);	//TODO esto para que se usa?

	default boolean receiveSlayerAttack(int damage) {return false;}
	
	default boolean receiveVampireAttack(int damage) {return false;}
}
