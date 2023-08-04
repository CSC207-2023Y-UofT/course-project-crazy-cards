package ui.windows;

import javax.swing.*;

import java.awt.CardLayout;

public class CardLayoutManager implements ICardLayoutManager {
    private JFrame frame;

    public CardLayoutManager(JFrame window) {
        this.frame = window;
    }

    @Override
    public void setPane(String pane) {
        CardLayout layout = getLayout();
        layout.show(frame.getContentPane(), pane);
    }

    @Override
    public void addPane(Window window) {
        CardLayout layout = getLayout();
        layout.addLayoutComponent(window.getPanel(), window.getIdentifier());
    }

    private CardLayout getLayout() {
        return (CardLayout)frame.getContentPane().getLayout();
    }
}
