package exceptions;

public class CommandParseException extends GameException {

	public CommandParseException(String str, Throwable cause) {
		super(str, cause);
	}
	
	
	public CommandParseException(String str) {
		super(str);
	}
	
	
	
}
