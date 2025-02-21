import java.awt.*;
import java.awt.event.*;

public class GUI extends WindowAdapter implements ActionListener {

    Frame frame;
    Label display, displayTop;
    final Color BUTTON_COLOR = new Color(0x94C5DC);
    final int BUTTON_SIZE = 50;
    final int BUTTON_GAP = 75;

    public GUI() {
        // creating a Frame
        frame = new Frame();
        frame.setSize(375, 570);
        frame.setBackground(new Color(0xE5E5E5));

        frame.addWindowListener(this);

        displayTop = new Label();
        displayTop.setBounds(50, 75, 275, 25);
        displayTop.setBackground(Color.LIGHT_GRAY);
        displayTop.setAlignment(Label.RIGHT);

        display = new Label("0");
        display.setBounds(50, 100, 275, 50);
        display.setBackground(Color.LIGHT_GRAY);
        display.setAlignment(Label.RIGHT);


        // creating Buttons
        String[] buttonLabels = {
                "CE", "back", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "="
        };

        Button[] buttons = new Button[buttonLabels.length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
        }

        // positioning
        int x = 50;
        int y = 175;

        // first row
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

        // second row
        for (int i = 3; i < 7; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }

        x = 50;
        y += BUTTON_GAP;

        // third row
        for (int i = 7; i < 11; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }

        x = 50;
        y += BUTTON_GAP;

        // fourth row
        for (int i = 11; i < 15; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }

        x = 50;
        y += BUTTON_GAP;

        // fifth row
        for (int i = 15; i < buttons.length - 1; i++) {
            buttons[i].setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
            x += BUTTON_GAP;
        }
        buttons[buttons.length - 1].setBounds(x, y, 125, BUTTON_SIZE);


        // adding components

        frame.add(display);
        frame.add(displayTop);

        // adding buttons, eventListeners and color
        for (Button button : buttons) {
            frame.add(button);
            button.addActionListener(this);
            if (button.getActionCommand().equals("CE") || button.getActionCommand().equals("=")) {
                button.setBackground(BUTTON_COLOR);
            }
        }

        // setting the title of frame
        frame.setTitle("My Calculator");

        // layout
        frame.setLayout(null);

        // setting visibility of frame
        frame.setVisible(true);
    }

    private void reset() {
        display.setText("0");
        displayTop.setText("");
    }

    public void windowClosing(WindowEvent e) {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String displayText = !display.getText().equals("0") ? display.getText() : "";

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
            if (ex.getClass() == ArithmeticException.class) {
                display.setText("Teilen durch 0 nicht möglich");
            }
        }
    }


}
