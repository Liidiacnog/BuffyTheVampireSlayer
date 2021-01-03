package exceptions;

public class UnknownCommandException extends CommandParseException {

	public UnknownCommandException(String str) {
		super(str);
	}

}
