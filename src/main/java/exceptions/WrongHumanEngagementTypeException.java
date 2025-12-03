package exceptions;

public class WrongHumanEngagementTypeException extends RuntimeException {
    public WrongHumanEngagementTypeException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return "This action can't be performed due to Human condition!";
    }
}
