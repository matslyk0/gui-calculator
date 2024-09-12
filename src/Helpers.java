import java.util.Arrays;
import java.util.List;
import java.text.DecimalFormat;

public class Helpers {

    static String[] operationsArray = {"+", "-", "÷", "×", "."};
    static List<String> operations = Arrays.asList(operationsArray);
    static DecimalFormat oneNine = new DecimalFormat("#.#########");
    static DecimalFormat twoEight = new DecimalFormat("##.########");
    static DecimalFormat threeSeven = new DecimalFormat("###.#######");
    static DecimalFormat fourSix = new DecimalFormat("####.######");
    static DecimalFormat fiveFive = new DecimalFormat("#####.#####");
    static DecimalFormat sixFour = new DecimalFormat("######.####");
    static DecimalFormat sevenThree = new DecimalFormat("#######.###");
    static DecimalFormat eightTwo = new DecimalFormat("########.##");
    static DecimalFormat nineOne = new DecimalFormat("#########.#");
    static DecimalFormat tenZero = new DecimalFormat("##########");
    static DecimalFormat scientific = new DecimalFormat("0.#########E0");
    static DecimalFormat[] formats = {
            oneNine, twoEight, threeSeven, fourSix, fiveFive,
            sixFour, sevenThree, eightTwo, nineOne, tenZero,
            scientific
    };

    static char getLastChar(String text) {
        return text.charAt(text.length() - 1);
    }

    static boolean isOperation(char a) {
        return operations.contains("" + a);
    }

    static String cutLastChar(String text) {
        return text.substring(0, text.length() - 1);
    }

    static String cutFirstChar(String text) {
        return text.substring(1);
    }

    static boolean endsWithOperation(String text) {
        char lastChar = getLastChar(text);
        return operations.contains(Character.toString(lastChar));
    }

    static String formatter(String num) {
        Double numAsDouble;
        try {numAsDouble = Double.parseDouble(num);}
        catch (NumberFormatException _) {return "";}
        int leadingDigits = num.indexOf(".");

        if (leadingDigits == -1) {
            leadingDigits = num.length();
            if (leadingDigits <= 10) return formats[leadingDigits - 1].format(numAsDouble);
            else return formats[10].format(numAsDouble);
        }

        if (num.startsWith("0.") || num.startsWith("0.0")) {
            if (!num.startsWith("0.00")) return formats[0].format(numAsDouble);
            else return formats[10].format(numAsDouble);
        }

        return formats[leadingDigits - 1].format(numAsDouble);
    }
}