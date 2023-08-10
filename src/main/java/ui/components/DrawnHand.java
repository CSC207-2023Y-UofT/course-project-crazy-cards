package ui.components;

import javax.swing.JPanel;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.util.List;

public class DrawnHand extends JPanel {
    private JLayeredPane cardPane;

    private List<DrawnCard> drawnCards;

    public DrawnHand(List<DrawnCard> drawnCards) {
        this.drawnCards = drawnCards;

        initializeGUIComponents();

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                updateCards();
            }

            public void componentMoved(java.awt.event.ComponentEvent e) {
                ; // Do nothing
            }

            public void componentShown(java.awt.event.ComponentEvent e) {
                ; // Do nothing
            }

            public void componentHidden(java.awt.event.ComponentEvent e) {
                ; // Do nothing
            }
        });
    }

    @Override
    public void addMouseListener(MouseListener listener) {
        for (DrawnCard card : drawnCards) {
            card.addMouseListener(listener);
        }
    }

    public void addCard(DrawnCard card) {
        drawnCards.add(card);
    }

    public void addCards(List<DrawnCard> cards) {
        drawnCards.addAll(cards);
    }

    public void removeCard(DrawnCard card) {
        drawnCards.remove(card);
    }

    private void initializeGUIComponents() {;
        setLayout(new BorderLayout());

        cardPane = new JLayeredPane();
        cardPane.setPreferredSize(new Dimension(300, 100));

        cardPane.setBorder(BorderFactory.createLineBorder(Color.RED, 8));
        add(cardPane);
    }
    
    public void updateCards() {
        int width = cardPane.getWidth();
        int height = cardPane.getHeight();
        double offset = width / drawnCards.size();

        cardPane.removeAll();

        for (int i = 0; i < drawnCards.size(); i++) {
            DrawnCard card = drawnCards.get(i);
            cardPane.add(card, i);

            card.setBounds((int)(i * offset + 28), (height / 2) -30, 50, 65);
        }
    }
}
