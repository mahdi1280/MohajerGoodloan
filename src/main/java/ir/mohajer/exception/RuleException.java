package ir.mohajer.exception;

public class RuleException extends RuntimeException {

    private ErrorMessage errorMessage;

    public RuleException(ErrorMessage errorMessage){
        this.errorMessage=errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
