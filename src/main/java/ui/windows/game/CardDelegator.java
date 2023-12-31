package ui.windows.game;

import controllers.interfaces.GameBridge;
import ui.components.DrawnCard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Delegates user interaction for selecting cards.
 */
public class CardDelegator implements MouseListener {
    private GameBridge bridge;
    private DrawnCard lastSelected;

    /**
     * Construct a CardDelegator with the given bridge.
     */
    public CardDelegator(GameBridge bridge) {
        this.bridge = bridge;
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
        bridge.setSelectedCard(card.getSuit(), card.getRank());

        if (lastSelected != null) {
            lastSelected.unhighlight();
        }
        card.highlight();
        lastSelected = card;
    }

    /**
     * Fires when the user hovers over a card.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        card.hover();
    }

    /**
     * Fires when the user stops hovering over a card.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        DrawnCard card = (DrawnCard)e.getSource();
        card.unhover();
    }
}
