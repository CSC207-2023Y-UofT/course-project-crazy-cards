package ui.windows.creation;

import controllers.GameBridge;
import controllers.GameCreationController;
import controllers.PlayerCreationInformation;
import controllers.PlayerGameController;
import enums.WindowName;
import ui.windows.layout_managers.CardLayoutManager;
import ui.windows.game.GameController;
import use_cases.PlayerGameInputBoundary;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class contains all the GUI/front-end logic for the game creation window/screen the User will see.
 */
public class CreationDisplay extends JPanel implements ActionListener {
    private JLabel giveNameMessage;
    private JLabel makeCPUorNo;
    private JPanel fieldsAndBoxes;
    private GameController gameController;
    private CardLayoutManager layoutManager;
    private final GameCreationController controller;
private final ArrayList<JCheckBox> checkBoxList = new ArrayList<>();
private final ArrayList<JTextField> textFieldList = new ArrayList<>();

    /**
     * Construct a new CreationDisplay.
     */
    public CreationDisplay(GameCreationController controller) {

        this.controller = controller;
        initializeGUIComponents();
    }
    public void setLayoutManager(CardLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }
    public GameCreationController getController() {
        return controller;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
    /**
     * Initialize the GUI components for the Game Creation display.
     */
    private void initializeGUIComponents() {
        // Set the layout for the overall JPanel and create the text-fields and checkboxes in the proper configuration.
        this.setLayout(new BorderLayout());
        this.initializeTextFields();
        this.initializeCheckBoxes();
        this.createFieldAndBoxPanels();

        // Create the button for submitting player info.
        JButton createGameButton = new JButton("Play Game!");
        createGameButton.addActionListener(this);
        createGameButton.setPreferredSize(new Dimension(800, 50));
        createGameButton.setFont(new Font("serif", Font.BOLD, 20));

        JLabel titleMessage = new JLabel("Game Creation!");
        titleMessage.setFont(new Font("serif", Font.BOLD, 35));

        this.add(titleMessage, BorderLayout.PAGE_START);
        this.add(createGameButton, BorderLayout.PAGE_END);
        this.add(fieldsAndBoxes, BorderLayout.LINE_START);
    }

    /**
     * Once text fields and checkboxes are made, add each pair of text field and checkbox into a separate JPanel
     * and add this JPanel to a larger JPanel representing both text fields and checkboxes.
     */
    private void createFieldAndBoxPanels() {
        fieldsAndBoxes = new JPanel();
        fieldsAndBoxes.setLayout(new BoxLayout(fieldsAndBoxes, BoxLayout.Y_AXIS));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        textPanel.add(giveNameMessage);
        textPanel.add(makeCPUorNo);
        fieldsAndBoxes.add(textPanel);

        for(int i = 0; i < 6; i++) {
            JTextField textField = this.textFieldList.get(i);
            JCheckBox checkBox = this.checkBoxList.get(i);
            JPanel fieldAndBox = new JPanel();
            fieldAndBox.setLayout(new BoxLayout(fieldAndBox, BoxLayout.X_AXIS));
            fieldAndBox.add(textField);
            fieldAndBox.add(checkBox);
            this.fieldsAndBoxes.add(fieldAndBox);
        }

    }

    /**
     * Create the header message and text-fields for each player name and add the text-fields to the list of text-fields
     */
    private void initializeTextFields() {
        this.giveNameMessage = new JLabel("Type in the names of the players.   ");
        this.giveNameMessage.setFont(new Font("serif", Font.BOLD, 20));
        Dimension dim = new Dimension(420, 50);
        Font font = new Font("serif", Font.PLAIN, 25);

        JTextField p1Name = new JTextField();
        this.textFieldList.add(p1Name);
        JTextField p2Name = new JTextField();
        this.textFieldList.add(p2Name);
        JTextField p3Name = new JTextField();
        this.textFieldList.add(p3Name);
        JTextField p4Name = new JTextField();
        this.textFieldList.add(p4Name);
        JTextField p5Name = new JTextField();
        this.textFieldList.add(p5Name);
        JTextField p6Name = new JTextField();
        this.textFieldList.add(p6Name);

        for(int i = 0; i < this.textFieldList.size(); i++) {
            JTextField textbox = this.textFieldList.get(i);
            textbox.setMaximumSize(dim);
            textbox.setFont(font);
        }
    }

    /**
     * Create all the checkboxes and add them to the list of checkboxes.
     */
    private void initializeCheckBoxes() {
        this.makeCPUorNo = new JLabel("Check the box to make player computer-controlled.");
        this.makeCPUorNo.setFont(new Font("serif", Font.BOLD, 20));

        JCheckBox p1isComp = new JCheckBox();
        this.checkBoxList.add(p1isComp);
        JCheckBox p2isComp = new JCheckBox();
        this.checkBoxList.add(p2isComp);
        JCheckBox p3isComp = new JCheckBox();
        this.checkBoxList.add(p3isComp);
        JCheckBox p4isComp = new JCheckBox();
        this.checkBoxList.add(p4isComp);
        JCheckBox p5isComp = new JCheckBox();
        this.checkBoxList.add(p5isComp);
        JCheckBox p6isComp = new JCheckBox();
        this.checkBoxList.add(p6isComp);

    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<PlayerCreationInformation> controllerInput = new ArrayList<>();
        // Take all the names that were entered, and add them alongside the option of whether to make the player
        // computer-controlled or not.
        for(int i = 0; i < 6; i ++) {
            String playerName = textFieldList.get(i).getText();
            boolean isComputerPlayer = checkBoxList.get(i).isSelected();
            // Make sure given name is not empty, otherwise, don't add it.
            if(playerName.length() > 0) {
                PlayerCreationInformation playerInfo = new PlayerCreationInformation(playerName, isComputerPlayer);
                controllerInput.add(playerInfo);
            }
        }
        boolean gameCreated = controller.createGameResponse(controllerInput);
        PlayerGameInputBoundary playerGameInteractor = controller.getPlayerGameInteractor();
        GameBridge bridge = new PlayerGameController(playerGameInteractor);
        gameController.setBridge(bridge);
        // If a Game was created (valid input for game players), then set the window to the Game.
        if(gameCreated) {
            gameController.startGame();
            layoutManager.setPane(WindowName.GAME);

        } else {
            JLabel message = new JLabel("Please enter valid input. That is, at least 2 players, 1 non-computer player, and no repeated names");
            this.add(message, BorderLayout.LINE_END);
        }

    }
}
