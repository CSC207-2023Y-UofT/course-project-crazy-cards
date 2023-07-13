package entities;

public interface Player {

    public int getNumCard();

    public String getName();

    public void putCard(Game game, Card card);

    public void setHand();
}
