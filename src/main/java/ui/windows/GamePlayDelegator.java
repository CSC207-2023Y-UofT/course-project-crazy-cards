package ui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.components.DrawnCard;

public class GamePlayDelegator implements ActionListener {
    private GameController controller;

    public GamePlayDelegator(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        controller.setSelectedCard(card.getSuit(), card.getRank());
    }
}
