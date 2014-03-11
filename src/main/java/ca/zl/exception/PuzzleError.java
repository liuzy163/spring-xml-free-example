package ca.zl.exception;

public class PuzzleError {
	private String errorMessage;

	PuzzleError(String error) {
		errorMessage = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String error) {
		errorMessage = error;
	}

}
