package entities;

public class ComputerPlayerFactory implements PlayerFactory {
    @Override
    public Player create(String name, Hand hand) {
        return new ComputerPlayer(name, hand);
    }
}
