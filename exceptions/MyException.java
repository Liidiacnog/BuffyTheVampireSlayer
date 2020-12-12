package exceptions;

public class MyException extends Exception {
	
	private String errorMsg;

	public MyException(String str) {
		errorMsg = str;
	}
	
	public String toString() {
		return errorMsg + '\n';
	}
}
