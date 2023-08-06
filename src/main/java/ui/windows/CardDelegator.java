package ui.windows;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.components.DrawnCard;

/**
 * Delegates user interaction for selecting cards.
 */
public class CardDelegator implements MouseListener {
    private GameController controller;

    /**
     * Construct a CardDelegator with the given controller.
     * @param controller the controller to be used
     */
    public CardDelegator(GameController controller) {
        this.controller = controller;
    }

    /**
     * Unused method.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        ; // Do nothing
    }

    /**
     * Unused method.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        ; // Do nothing
    }

    /**
     * Fires when the user requests to select a card.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        controller.setSelectedCard(card.getSuit(), card.getRank());
    }

    /**
     * Fires when the user hovers over a card.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        card.highlight();
    }

    /**
     * Fires when the user stops hovering over a card.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        card.unhighlight();
    }
}
