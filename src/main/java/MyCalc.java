import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyCalc extends WindowAdapter implements ActionListener {

    private final Frame f;
    private final Label display, displayTop;
    private static final Color c = new Color(0x94C5DC);
    private int operation;
    private double operant1;
    private static final int B_SIZE = 50;
    private static final int B_GAP = 75;

    // initializing using constructor
    MyCalc() {

        // creating a Frame
        f = new Frame();
        f.setSize(375, 570);
        f.setBackground(new Color(0xE5E5E5));

        f.addWindowListener(this);

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
                buttons[i].setBounds(x, y, 125, B_SIZE);
                x += B_GAP * 2;
            } else {
                buttons[i].setBounds(x, y, B_SIZE, B_SIZE);
                x += B_GAP;
            }
        }

        x = 50;
        y += B_GAP;

        // second row
        for (int i = 3; i < 7; i++) {
            buttons[i].setBounds(x, y, B_SIZE, B_SIZE);
            x += B_GAP;
        }

        x = 50;
        y += B_GAP;

        // third row
        for (int i = 7; i < 11; i++) {
            buttons[i].setBounds(x, y, B_SIZE, B_SIZE);
            x += B_GAP;
        }

        x = 50;
        y += B_GAP;

        // fourth row
        for (int i = 11; i < 15; i++) {
            buttons[i].setBounds(x, y, B_SIZE, B_SIZE);
            x += B_GAP;
        }

        x = 50;
        y += B_GAP;

        // fifth row
        for (int i = 15; i < buttons.length; i++) {
            if (!buttonLabels[i].equals("=")) {
                buttons[i].setBounds(x, y, B_SIZE, B_SIZE);
                x += B_GAP;
            } else {
                buttons[i].setBounds(x, y, 125, B_SIZE);
            }
        }


        // adding components

        f.add(display);
        f.add(displayTop);

        // adding buttons, eventListeners and color
        for (Button button : buttons) {
            f.add(button);
            button.addActionListener(this);
            if (button.getActionCommand().equals("CE") || button.getActionCommand().equals("=")) {
                button.setBackground(c);
            }
        }

        // setting the title of frame
        f.setTitle("My Calculator");

        // layout
        f.setLayout(null);

        // setting visibility of frame
        f.setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String displayText = !display.getText().equals("0") ? display.getText() : "";

        switch (command) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> display.setText(displayText + command);
            case "back" -> display.setText(!displayText.isEmpty() ? displayText.substring(0, displayText.length() - 1) : "0");
            case "+" -> operation(1, command);
            case "-" -> operation(2, command);
            case "*" -> operation(3, command);
            case "/" -> operation(4, command);
            case "=" -> display.setText(calculate());
            default -> reset();
        }
    }

    private void operation(int operationNum, String command) {
        operant1 = Double.parseDouble(display.getText());
        displayTop.setText(display.getText() + command);
        display.setText("");
        operation = operationNum;
    }

    private String calculate() {
        double operant2 = Double.parseDouble(display.getText());
        displayTop.setText(displayTop.getText() + display.getText() + "=");

        double result = switch (operation) {
            case 1 -> operant1 + operant2;
            case 2 -> operant1 - operant2;
            case 3 -> operant1 * operant2;
            case 4 -> operant1 / operant2;
            default -> 0;
        };
        return (result * 10) % 10 == 0 ? String.valueOf((int) result) : String.valueOf(result);
    }

    private void reset() {
        display.setText("0");
        displayTop.setText("");
    }

    public static void main(String[] args) {
        new MyCalc();
    }
}
