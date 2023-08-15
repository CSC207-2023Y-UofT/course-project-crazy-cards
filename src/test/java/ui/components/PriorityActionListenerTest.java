package ui.components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to test PriorityActionListener
 * This class is tested as follows:
 * We have 3 listeners of different priorities added to a PriorityActionListener, each of them modify the same
 * variable, if we have the action performed on the PriorityActionListener, it should be that the last change
 * should reflect that of the lowest priority listener.
 */
class PriorityActionListenerTest {

    private ActionListener listener1 = new Listener1();
    private ActionListener listener2 = new Listener2();
    private ActionListener listener3 = new Listener3();
    private PriorityActionListener actionListener = new PriorityActionListener();
    private Event e;

    /**
     * Dummy event class for testing variable change.
     */
    private static class Event extends JPanel {
        private final JButton button = new JButton("0");
        public Event(PriorityActionListener actionListener) {
            this.button.addActionListener(actionListener);
        }
        public JButton getButton() {
            return this.button;
        }

    }

    // Defined below 3 different ActionListeners that modify the same variable
    private static class Listener1 implements ActionListener {

        /**
         * Invoked when an action occurs.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setText("1");
        }
    }
    private static class Listener2 implements ActionListener {

        /**
         * Invoked when an action occurs.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setText("2");
        }
    }
    private static class Listener3 implements ActionListener {

        /**
         * Invoked when an action occurs.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setText("3");
        }
    }

    /**
     * Set up an event to test.
     */
    @BeforeEach
    public void setUp() {
        e = new Event(actionListener);
    }

    /**
     * Set all objects made to null to free memory.
     */
    @AfterEach
    public void tearDown() {
        e = null;
        actionListener = null;
        listener1 = null;
        listener2 = null;
        listener3 = null;
    }


    /**
     * Test addActionListener() and removeActionListener() keep to the priorities of the action listeners (list of
     * action listeners is sorted).
     */
    @Test
    public void testAddAndRemoveActionListener() {
        actionListener.addActionListener(listener3, 3);
        e.getButton().doClick();
        assertEquals("3", e.getButton().getText());

        actionListener.addActionListener(listener2, 2);
        e.getButton().doClick();
        assertEquals("2", e.getButton().getText());

        actionListener.addActionListener(listener1, 1);
        e.getButton().doClick();
        assertEquals("1", e.getButton().getText());

        actionListener.removeActionListener(listener3);
        e.getButton().doClick();
        assertEquals("1", e.getButton().getText());

        actionListener.removeActionListener(listener1);
        e.getButton().doClick();
        assertEquals("2", e.getButton().getText());

    }
}