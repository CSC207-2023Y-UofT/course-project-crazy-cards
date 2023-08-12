import controllers.GameBridge;
import controllers.GameCreationController;
import database.CSVDatabase;
import enums.WindowName;
import ui.factories.*;
import ui.windows.Window;
import ui.windows.creation.CreationDisplay;
import ui.windows.game.GameController;
import ui.windows.game.GameDisplay;
import ui.windows.layout_managers.CardLayoutManager;
import ui.windows.layout_managers.PaneDelegator;
import ui.windows.stats.StatsController;
import ui.windows.stats.StatsDisplay;
import use_cases.DataAccess;
import use_cases.GameCreationInteractor;
import use_cases.PlayerGameInteractor;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        CardLayoutManager layoutManager = new CardLayoutManager(frame);

        MenuWindowFactory menuFactory = new MenuWindowFactory();
        RuleWindowFactory ruleFactory = new RuleWindowFactory();
        StatsWindowFactory statsFactory = new StatsWindowFactory();
        GameWindowFactory factory = new GameWindowFactory();
        CreationWindowFactory creationWindowFactory = new CreationWindowFactory();

        Window menuWindow = menuFactory.createWindow();
        Window ruleWindow = ruleFactory.createWindow();
        Window statsWindow = statsFactory.createWindow();
        Window creationWindow = creationWindowFactory.createWindow();
        DataAccess database = new CSVDatabase();
        CreationDisplay creationDisplay = (CreationDisplay) creationWindow.getPanel();
        GameCreationController creationController =  creationDisplay.getController();
        GameCreationInteractor creationInteractor = creationController.getInteractor();
        creationInteractor.setDataAccess(database);
        StatsDisplay statsDisplay = (StatsDisplay) statsWindow.getPanel();
        StatsController controller = statsDisplay.getDelegator().getController();
        controller.setDatabase(database);
        Window gameWindow = factory.createWindow();


        layoutManager = new CardLayoutManager(frame);
        layoutManager.addPane(menuWindow);
        layoutManager.addPane(ruleWindow);
        layoutManager.addPane(statsWindow);
        layoutManager.addPane(gameWindow);
        layoutManager.addPane(creationWindow);

        PaneDelegator paneDelegator = new PaneDelegator(layoutManager);
        menuWindow.setNavigator(paneDelegator);
        statsWindow.setNavigator(paneDelegator);
        ruleWindow.setNavigator(paneDelegator);

        GameDisplay gameDisplay = (GameDisplay) gameWindow.getPanel();
        GameController gameController = gameDisplay.getController();
        creationDisplay.setGameController(gameController);
        creationDisplay.setLayoutManager(layoutManager);


        frame.setVisible(true);

        PlayerGameInteractor playerGameInteractor = null;
        while (playerGameInteractor == null) {
            playerGameInteractor = creationController.getPlayerGameInteractor();
        }
        gameController.setBridge((GameBridge) playerGameInteractor);


        layoutManager.setPane(WindowName.GAME);
    }
}
