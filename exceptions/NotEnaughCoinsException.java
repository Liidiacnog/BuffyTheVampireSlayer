package exceptions;

public class NotEnaughCoinsException extends CommandExecuteException {

	public NotEnaughCoinsException(String el, int cost) {
		super("[DEBUG] " + el + " cost is " + cost + ": Not enaugh coins", el.toLowerCase());
	}

}
