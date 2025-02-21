import java.awt.*;
import java.awt.event.*;

public class GUI extends WindowAdapter implements ActionListener {

    // Frame for main window and two labels for displays
    Frame frame;
    Label display, displayTop;
    // Constants for button size, spacing and color
    final int BUTTON_SIZE = 50;
    final int BUTTON_GAP = 75;
    final Color BUTTON_COLOR = new Color(0x94C5DC);

    // Constructor
    public GUI() {
        // Initialize the main window with size and background color
        frame = new Frame();
        frame.setSize(375, 570);
        frame.setBackground(new Color(0xE5E5E5));
        frame.addWindowListener(this);

        // Top display for intermediate results or entered values
        displayTop = new Label();
        displayTop.setBounds(50, 75, 275, 25);
        displayTop.setBackground(Color.LIGHT_GRAY);
        displayTop.setAlignment(Label.RIGHT);

        // Main display for the current result or input
        display = new Label("0");
        display.setBounds(50, 100, 275, 50);
        display.setBackground(Color.LIGHT_GRAY);
        display.setAlignment(Label.RIGHT);


        // Array containing button labels
        String[] buttonLabels = {
                "CE", "back", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "="
        };

        //Creating array of buttons
        Button[] buttons = new Button[buttonLabels.length];

        // Initialize buttons with their respective labels
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
        }


        // Position buttons in multiple rows
        // Start-coordinates
        int x = 50;
        int y = 175;

        // First row with "CE", "back" and "/"
        for (int i = 0; i < 3; i++) {
            if (buttonLabels[i].equals("CE")) {
                buttons[i].setBounds(x, y, 125, BUTTON_SIZE);
                x += BUTTON_GAP * 2;
            } else {
                buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
                x += BUTTON_GAP;
            }
        }

        x = 50;
        y += BUTTON_GAP;

        // Second row with "7", "8", "9" and "*"
        for (int i = 3; i < 7; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }

        x = 50;
        y += BUTTON_GAP;

        // Third row with "4", "5", "6" and "-"
        for (int i = 7; i < 11; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }

        x = 50;
        y += BUTTON_GAP;

        // Fifth row with "1", "2", "3" and "+"
        for (int i = 11; i < 15; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }

        x = 50;
        y += BUTTON_GAP;

        // Sixth row with "0", "." and "="
        for (int i = 15; i < buttons.length - 1; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }
        buttons[buttons.length - 1].setBounds(x, y, 125, BUTTON_SIZE);

        // Adding components
        // Adding both displays
        frame.add(display);
        frame.add(displayTop);

        // Adding buttons
        for (Button button : buttons) {
            frame.add(button);
            // Assigns event listeners to buttons and sets special colors for certain buttons
            button.addActionListener(this);
            if (button.getActionCommand().equals("CE") || button.getActionCommand().equals("=")) {
                button.setBackground(BUTTON_COLOR);
            }
        }

        // Setting the title of frame
        frame.setTitle("My Calculator");

        // Layout
        frame.setLayout(null);

        // Setting visibility of frame
        frame.setVisible(true);
    }

    /**
     * Resets both displays with "0" respectively with an empty String.
     */
    private void reset() {
        display.setText("0");
        displayTop.setText("");
    }


    public void windowClosing(WindowEvent e) {
        frame.dispose();
    }

    /**
     * Retrieves the command of the pressed button and handles the display.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String displayText = !display.getText().equals("0") ? display.getText() : "";

        // Processes user input, updates the text shown in display and performs calculations
        try {
            switch (command) {
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> display.setText(displayText + command);
                case "back" ->
                        display.setText(!displayText.isEmpty() ? displayText.substring(0, displayText.length() - 1) : "0");
                case "+" -> Calculate.operation(1, command, this);
                case "-" -> Calculate.operation(2, command, this);
                case "*" -> Calculate.operation(3, command, this);
                case "/" -> Calculate.operation(4, command, this);
                case "=" -> display.setText(Calculate.calculate(this));
                default -> reset();
            }
        } catch (Exception ex) {
            // Error handling for division by zero
            if (ex.getClass() == ArithmeticException.class) {
                display.setText("Teilen durch 0 nicht möglich");
            }
        }
    }


}
