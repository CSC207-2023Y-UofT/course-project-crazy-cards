package ui.components;

import java.util.List;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PriorityActionListener implements ActionListener {
    private List<ListenerPriorityPair> listeners;

    public PriorityActionListener() {
        listeners = new ArrayList<ListenerPriorityPair>();
    }

    public void addActionListener(ActionListener listener, int priority) {
        insertSorted(new ListenerPriorityPair(listener, priority));
    }

    public boolean removeActionListener(ActionListener listener) {
        return removeSorted(listener);
    }

    public void clear() {
        listeners.clear();
    }

    private void insertSorted(ListenerPriorityPair pair) {
        int i = listeners.size();
        while (i > 0 && listeners.get(i - 1).getPriority() < pair.getPriority()) {
            i--;
        }
        listeners.add(i, pair);
    }

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

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        for (ListenerPriorityPair pair : listeners) {
            pair.getListener().actionPerformed(e);
        }
    }
}

class ListenerPriorityPair {
    private ActionListener listener;
    private int priority;

    public ListenerPriorityPair(ActionListener listener, int priority) {
        this.listener = listener;
        this.priority = priority;
    }

    public ActionListener getListener() {
        return listener;
    }

    public int getPriority() {
        return priority;
    }
}
