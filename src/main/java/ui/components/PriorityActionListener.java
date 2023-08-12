package ui.components;

import java.util.List;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is a wrapper for an ActionListener that allows for multiple listeners to be added to a single component.
 * The listeners are executed in order of their priority, with higher priority being executed first.
 */
public class PriorityActionListener implements ActionListener {
    private List<ListenerPriorityPair> listeners;

    /**
     * Constructor for a PriorityActionListener.
     */
    public PriorityActionListener() {
        listeners = new ArrayList<ListenerPriorityPair>();
    }

    /**
     * Adds a listener to this instance.
     * @param listener The listener to add.
     * @param priority The priority of the listener. Higher priority listeners are executed first.
     */
    public void addActionListener(ActionListener listener, int priority) {
        insertSorted(new ListenerPriorityPair(listener, priority));
    }

    /**
     * Removes a listener from this instance.
     * @param listener The listener to remove.
     * @return True if the listener was removed, false if it was not found.
     */
    public boolean removeActionListener(ActionListener listener) {
        return removeSorted(listener);
    }

    /**
     * Removes all listeners from this instance.
     */
    public void clear() {
        listeners.clear();
    }

    /**
     * Helper method to insert a listener into the list in sorted order.
     * @param pair The listener to insert.
     */
    private void insertSorted(ListenerPriorityPair pair) {
        int i = listeners.size();
        while (i > 0 && listeners.get(i - 1).getPriority() < pair.getPriority()) {
            i--;
        }
        listeners.add(i, pair);
    }

    /**
     * Helper method to remove a listener from the list.
     * @param listener The listener to remove.
     * @return True if the listener was removed, false if it was not found.
     */
    private boolean removeSorted(ActionListener listener) {
        int i = 0;
        while (i < listeners.size() && listeners.get(i).getListener() != listener) {
            i++;
        }
        if (i < listeners.size()) {
            listeners.remove(i);
            return true;
        }
        return false;
    }

    /**
     * Executes all listeners in order of their priority.
     * @param e The event to pass to the listeners.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        for (ListenerPriorityPair pair : listeners) {
            pair.getListener().actionPerformed(e);
        }
    }
}

/**
 * Private class to store a listener and its priority.
 */
class ListenerPriorityPair {
    private ActionListener listener;
    private int priority;

    /**
     * Constructor for a ListenerPriorityPair.
     * @param listener the listener to be stored
     * @param priority the priority of the listener
     */
    public ListenerPriorityPair(ActionListener listener, int priority) {
        this.listener = listener;
        this.priority = priority;
    }

    /**
     * Getter for the listener.
     * @return the listener
     */
    public ActionListener getListener() {
        return listener;
    }

    /**
     * Getter for the priority.
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }
}
