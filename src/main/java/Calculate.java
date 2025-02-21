
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculate {
    static int operationNum;
    static BigDecimal operand1;

    static void operation(int num, String command, GUI gui) {
        operand1 = new BigDecimal(gui.display.getText());
        gui.displayTop.setText(gui.display.getText() + command);
        gui.display.setText("");
        operationNum = num;
    }

    static String calculate(GUI gui) {
        BigDecimal operand2 = new BigDecimal(gui.display.getText());
        gui.displayTop.setText(gui.displayTop.getText() + gui.display.getText() + "=");

        BigDecimal result = switch (operationNum) {
            case 1 -> operand1.add(operand2);
            case 2 -> operand1.subtract(operand2);
            case 3 -> operand1.multiply(operand2);
            case 4 -> operand1.divide(operand2, 15, RoundingMode.HALF_UP);
            default -> new BigDecimal(0);
        };

        return String.valueOf(result);
    }
}
