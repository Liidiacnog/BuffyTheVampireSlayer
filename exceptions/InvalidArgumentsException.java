package exceptions;

public class InvalidArgumentsException extends CommandParseException {

	public InvalidArgumentsException(String str) {
		super(str);
	}

}
