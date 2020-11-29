package logic.gameObjects;

public interface IAttack {

	void attack(int columns); //TODO: what would happen if it weren't in this interface? Ok to pass it the oclumns though Vampire won't use them?

	default boolean receiveSlayerAttack(int damage) {return false;}
	
	default boolean receiveVampireAttack(int damage) {return false;}
}
