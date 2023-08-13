package ui.components;

import enums.Rank;
import enums.Suit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.util.List;

public class DrawnHand extends JPanel {
    private JLayeredPane cardPane;

    private int visible;
    private List<DrawnCard> drawnCards;

    /**
     * Constructor for DrawnHand that displays a player's hand on the screen.
     * @param drawnCards The list of DrawnCards within the user's hand about to be displayed.
     */
    public DrawnHand(List<DrawnCard> drawnCards) {
        this.drawnCards = drawnCards;

        initializeGUIComponents();

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                updateCards();
            }

            public void componentMoved(java.awt.event.ComponentEvent e) {
                // Do nothing
            }

            public void componentShown(java.awt.event.ComponentEvent e) {
                // Do nothing
            }

            public void componentHidden(java.awt.event.ComponentEvent e) {
                // Do nothing
            }
        });
    }

    /**
     * Add a mouse listener to each card for gameplay purposes.
     * @param listener   the mouse listener
     */
    @Override
    public void addMouseListener(MouseListener listener) {
        for (DrawnCard card : drawnCards) {
            card.addMouseListener(listener);
        }
    }

    /**
     * Add a given card to the DrawnHand, in order to be displayed.
     * @param card
     */
    public void addCard(DrawnCard card) {
        drawnCards.add(card);
    }

    /**
     * Initializes the GUI components necessary for the DrawnHand to be displayed.
     */
    private void initializeGUIComponents() {
        setLayout(new BorderLayout());

        cardPane = new JLayeredPane();
        cardPane.setPreferredSize(new Dimension(300, 100));

        cardPane.setBorder(BorderFactory.createLineBorder(Color.RED, 8));
        add(cardPane);
    }

    /**
     * Sets a new display for a given DrawnCard within the user's DrawnHand to be displayed.
     * @param index Index of the specific DrawnCard to be updated, in the List of DrawnCards within the user's hand.
     * @param suit The new suit of the card that is being updated.
     * @param rank The new rank/value of the card that is being updated.
     */
    public void setCard(int index, Suit suit, Rank rank) {
        DrawnCard card = drawnCards.get(index);
        card.setSuit(suit);
        card.setRank(rank);
        card.setSuitLabel(suit);
        card.setRankLabel(rank);
        card.setVisible(true);
        // TODO: justDrawn implementation
        updateCards();
    }

    /**
     * Hide all cards after the given index, inclusive.
     * @param index The index of the card to start hiding from.
     */
    public void hideCards(int index) {
        visible = index;
        for (int i = index; i < drawnCards.size(); i++) {
            DrawnCard card = drawnCards.get(i);
            card.setVisible(false);
        }
    }

    /**
     * Update the display of the DrawnHand by setting new positions of each card and changing the display between users.
     */
    public void updateCards() {
        int width = cardPane.getWidth();
        int height = cardPane.getHeight();
        double offset = (double) width / visible;

        cardPane.removeAll();

        for (int i = 0; i < visible; i++) {
            DrawnCard card = drawnCards.get(i);
            cardPane.add(card, i);

            card.setBounds((int)(i * offset + 10), (height / 2) - 30, 70, 95);
        }
    }
}
