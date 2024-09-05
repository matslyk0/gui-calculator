import java.util.Arrays;
import java.util.List;
import java.text.DecimalFormat;

public class Helpers {

    static String[] operationsArray = {"+","-","÷","×","."};
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
    static DecimalFormat[] formats = {
            oneNine, twoEight, threeSeven, fourSix, fiveFive,
            sixFour, sevenThree, eightTwo, nineOne, tenZero
    };

    static char getLastChar(String text) {return text.charAt(text.length()-1);}

    static boolean isOperation(char a) {return operations.contains(""+a);}

    static String cutLastChar(String text) {return text.substring(0,text.length()-1);}

    static String cutFirstChar(String text) {return text.substring(1);}

    static boolean endsWithOperation(String text) {
        char lastChar = getLastChar(text);
        return operations.contains(Character.toString(lastChar));
    }

    static String formatter(String num) {
        //the number is rounded to a number of decimal places appropriate to the numbers size
        //trailing zeros are automatically removed
        int index = num.indexOf(".")-1;
        Double numDouble = Double.parseDouble(num);
        if (index==-2) return num;
        return formats[index].format(numDouble);
        //will improve readability
    }
}

