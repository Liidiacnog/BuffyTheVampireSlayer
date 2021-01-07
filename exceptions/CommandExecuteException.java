package exceptions;

public class CommandExecuteException extends GameException {

	public CommandExecuteException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public CommandExecuteException(String str) {
		super(str);
	}
	
	// Choose between add or release depending on the element
	private static String addOrRelease(String el) { //TODO needed?
		String str = "add";
		if (el.equals("garlic push") || el.equals("light flash"))
			str = "release";
		return str;
	}

}
