package entities;

public class HumanPlayerFactory implements PlayerFactory {
    @Override
    public Player create(String name, Hand hand) {
        return new HumanPlayer(name, hand, 0, 0) {
        };
    }
}
