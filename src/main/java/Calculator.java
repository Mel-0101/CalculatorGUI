
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    // Stores the current operation and the first operand as a BigDecimal for precise calculations
    private int operationNum;
    private BigDecimal operand1;

    /**
     * Stores the first operand and the selected operation when an operation is triggered.<br>
     * Both displays are updated accordingly.
     * @param num selected operation
     * @param command pressed button
     * @param gui GUI object
     */
    protected void operation(int num, String command, GUI gui) {
        operand1 = new BigDecimal(gui.display.getText());
        operationNum = num;
        gui.displayTop.setText(gui.display.getText() + command);
        gui.display.setText("");
    }

    /**
     * Performs the selected calculation when the second operand is entered.<br>
     * Updates the top display.
     * @param gui GUI object
     * @return result of the calculation as String
     */
    protected String calculate(GUI gui) {
        BigDecimal operand2 = new BigDecimal(gui.display.getText());
        gui.displayTop.setText(gui.displayTop.getText() + gui.display.getText() + "=");

        BigDecimal result = switch (operationNum) {
            case 1 -> operand1.add(operand2);
            case 2 -> operand1.subtract(operand2);
            case 3 -> operand1.multiply(operand2);
            // Division is rounded to 15 decimal places
            case 4 -> operand1.divide(operand2, 15, RoundingMode.HALF_UP);
            default -> new BigDecimal(0);
        };

        return String.valueOf(result);
    }
}
