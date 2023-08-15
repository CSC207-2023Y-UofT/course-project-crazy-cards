package controllers.interfaces;

import ui.interfaces.StatsUI;

public interface StatsBridge {
    void tryRequestUser(String username);

    void setUI(StatsUI ui);
}