package entities;

public interface PlayerFactory {
    Player create(String name, Hand hand);
}
