package exception;

public class AccountNotFoundException extends Exception {

	private String message;
	
	public AccountNotFoundException() {
		this.message = "The account with the specified id could not be found.";
	}
	
	public AccountNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}