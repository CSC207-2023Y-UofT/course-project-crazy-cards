package entities;

public interface PlayerFactory {
    /**
     * Create a Player upon calling this method.
     * @param name The name of the Player that will be created.
     * @param hand The hand dealt to the Player that will be created.
     */
    Player create(String name, Hand hand);
}
