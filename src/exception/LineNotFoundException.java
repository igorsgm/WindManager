package exception;

@SuppressWarnings("serial")
public class LineNotFoundException extends Exception {

	private String message;
	
	public LineNotFoundException() {
		this.message = "The specific line could not be found.";
	}
	
	public LineNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}