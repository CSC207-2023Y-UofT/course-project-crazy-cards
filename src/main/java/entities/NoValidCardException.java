package entities;

public class NoValidCardException extends Exception{
    public NoValidCardException(String msg) {
        super(msg);
    }
}
