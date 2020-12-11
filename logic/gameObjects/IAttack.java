package logic.gameObjects;

public interface IAttack {

	default void attack() {}

	default boolean receiveSlayerAttack(int damage) {return false;}
	
	default boolean receiveVampireAttack(int damage) {return false;}
	
	default boolean receiveDraculaAttack() {return false;}
	
	default boolean receiveVampireExplosion(int damage) {return false;}
}
