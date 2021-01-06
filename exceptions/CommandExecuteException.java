package exceptions;

public class CommandExecuteException extends GameException {

	public CommandExecuteException(String Msg, String el) {
		super("[ERROR] failed to " + addOrRelease(el) + " " + el + "%n" + Msg);
	}

	// Choose between add or release dependign on the element
	private static String addOrRelease(String el) {
		String str = "add";
		if (el.equals("garlic push") || el.equals("light flash"))
			str = "release";
		return str;
	}

}
