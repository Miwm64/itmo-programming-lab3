package exceptions;

public class BackpackAlreadyExistsException extends RuntimeException {
    public BackpackAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return "Backpack already exists! Remove it first";
    }
}
