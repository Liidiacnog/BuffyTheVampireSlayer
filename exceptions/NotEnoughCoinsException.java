package exceptions;

/* thrown when an attempt is made to execute any command which has a cost in coins but the user does not have enough 
 * coins to cover that cost. */

public class NotEnoughCoinsException extends CommandExecuteException {

	public NotEnoughCoinsException(String str) {
		super(str); 
	}
}