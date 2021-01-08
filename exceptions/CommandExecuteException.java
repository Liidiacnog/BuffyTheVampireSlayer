package exceptions;

public class CommandExecuteException extends GameException {

	public CommandExecuteException() {
		super();
	}
	
	public CommandExecuteException(String str) {
		super(str);
	}
	
	public CommandExecuteException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public CommandExecuteException(Throwable cause) {
		super(cause);
	}
	
	public CommandExecuteException(String str, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(str, cause, enableSuppression, writableStackTrace);
	}
	
	
	// Choose between add or release depending on the element
	private static String addOrRelease(String el) { //TODO needed?
		String str = "add";
		if (el.equals("garlic push") || el.equals("light flash"))
			str = "release";
		return str;
	}

}
