package ui.windows;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.components.DrawnCard;

public class CardDelegator implements MouseListener {
    private GameController controller;

    public CardDelegator(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ; // Do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ; // Do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        controller.setSelectedCard(card.getSuit(), card.getRank());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        card.highlight();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        card.unhighlight();
    }
}
