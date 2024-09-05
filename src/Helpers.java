import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helpers {

    static String[] operationsArray = {"+","-","÷","×","."};
    static List<String> operations = Arrays.asList(operationsArray);

    static char getLastChar(String text) {return text.charAt(text.length()-1);}

    static boolean isOperation(char a) {return operations.contains(""+a);}

    static String cutLastChar(String text) {return text.substring(0,text.length()-1);}

    static String cutFirstChar(String text) {return text.substring(1);}

    static boolean endsWithOperation(String text) {
        char lastChar = getLastChar(text);
        return operations.contains(Character.toString(lastChar));
    }

    static String rounder(String num) {
        try {
            BigDecimal bdNum = new BigDecimal(num);
            bdNum = bdNum.setScale(6,RoundingMode.HALF_UP);
            String stringNum = bdNum.toString();
            while (stringNum.endsWith("0")) stringNum = cutLastChar(stringNum);
            if (stringNum.endsWith(".")) stringNum = cutLastChar(stringNum);
            return stringNum;
        }
        catch (NumberFormatException | NullPointerException ex) {
            //System.out.println("issue when rounding");
            return "";
        }
        catch (Exception ex) {
            //System.out.println("Something unexpected went wrong: "+ex);
            return "";
        }
    }
}

