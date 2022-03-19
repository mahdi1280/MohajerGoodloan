package ir.mohajer.exception;

public class ErrorMessage {

    private final String message;
    private final String code;

    private ErrorMessage(String message,String code){
        this.message=message;
        this.code=code;
    }

    public static ErrorMessage error(String message,String code){
        return new ErrorMessage(message,code);
    }

    public static ErrorMessage error(String message){
        return new ErrorMessage(message,message);
    }
}
