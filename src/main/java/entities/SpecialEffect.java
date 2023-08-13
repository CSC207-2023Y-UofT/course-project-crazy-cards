package entities;

public interface SpecialEffect {
    void action(GameAccess game);

    boolean getAlwaysPlayable();
}
