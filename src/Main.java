import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.parser.ParseException;

public class Main {

    public static void main(String[] args) {

        Screen screen = new Screen();

    }

}

class Screen extends JFrame implements ActionListener {
    String entry = "0";
    String result = "";
    Expression expression;
    EvaluationValue calculation;
    boolean decimalPlaced = false;

    JButton filler1 = new JButton("[]");
    JButton filler2 = new JButton("[]");
    JButton clear = new JButton("C");
    JButton delete = new JButton("DEL");
    JButton seven = new JButton("7");
    JButton eight = new JButton("8");
    JButton nine = new JButton("9");
    JButton divide = new JButton("÷");
    JButton four = new JButton("4");
    JButton five = new JButton("5");
    JButton six = new JButton("6");
    JButton multiply = new JButton("×");
    JButton one = new JButton("1");
    JButton two = new JButton("2");
    JButton three = new JButton("3");
    JButton add = new JButton("+");
    JButton zero = new JButton("0");
    JButton decimal = new JButton(".");
    JButton equals = new JButton("=");
    JButton subtract = new JButton("-");

    JPanel keys = new JPanel();
    JPanel calcScreen = new JPanel();

    JLabel resultBox = new JLabel(result);
    JLabel entryBox = new JLabel(entry);

    Screen() {

        JButton[] buttons = {
                filler1, filler2, clear, delete,
                seven, eight, nine, divide,
                four, five, six, multiply,
                one, two, three, add,
                zero, decimal, equals, subtract
        };
        for (JButton button : buttons) {
            button.setFocusPainted(false);
            button.addActionListener(this);
            button.setBackground(new Color(0x6F7378));
            button.setForeground(new Color(0xFFFFFF));
        }

        entryBox.setHorizontalAlignment(JLabel.LEFT);
        entryBox.setVerticalAlignment(JLabel.BOTTOM);
        entryBox.setFont(new Font("Arial", Font.PLAIN,20));
        entryBox.setOpaque(true);
        entryBox.setBackground(new Color(0x6F7378));

        resultBox.setHorizontalAlignment(JLabel.RIGHT);
        resultBox.setVerticalAlignment(JLabel.BOTTOM);
        resultBox.setFont(new Font("Arial", Font.PLAIN,20));
        resultBox.setOpaque(true);
        resultBox.setBackground(new Color(0x6F7378));

        calcScreen.setLayout(new GridLayout(2,1));
        calcScreen.add(entryBox);
        calcScreen.add(resultBox);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,450);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        keys.setLayout(new GridLayout(5,4));
        keys.setBackground(new Color(0x6F7378));
        for (JButton button : buttons) {keys.add(button);}

        //fitting the pieces together
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(calcScreen, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(keys, constraints);

        this.getContentPane().setBackground(new Color(0x6F7378));
        this.setVisible(true);
    }

    //keymapping
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==decimal && !decimalPlaced) {
            if (Helpers.endsWithOperation(entry)) entry += "0.";
            else entry += ".";
            decimalPlaced = true;
        }

        else if (e.getSource()==add) {
            if (Helpers.endsWithOperation(entry)) entry = Helpers.cutLastChar(entry);
            entry += "+";
            decimalPlaced = false;
        }

        else if (e.getSource()==subtract) {
            if (Helpers.endsWithOperation(entry)) entry = Helpers.cutLastChar(entry);
            entry += "-";
            decimalPlaced = false;
        }

        else if (e.getSource()==multiply) {
            if (Helpers.endsWithOperation(entry)) entry = Helpers.cutLastChar(entry);
            entry += "×";
            decimalPlaced = false;
        }

        else if (e.getSource()==divide) {
            if (Helpers.endsWithOperation(entry)) entry = Helpers.cutLastChar(entry);
            entry += "÷";
            decimalPlaced = false;
        }

        else if (e.getSource()==zero) entry += "0";
        else if (e.getSource()==one) entry += "1";
        else if (e.getSource()==two) entry += "2";
        else if (e.getSource()==three) entry += "3";
        else if (e.getSource()==four) entry += "4";
        else if (e.getSource()==five) entry += "5";
        else if (e.getSource()==six) entry += "6";
        else if (e.getSource()==seven) entry += "7";
        else if (e.getSource()==eight) entry += "8";
        else if (e.getSource()==nine) entry += "9";

        else if (e.getSource()==delete) {
            entry = Helpers.cutLastChar(entry);
            if (entry.isEmpty()) entry ="0";
        }

        else if (e.getSource()==clear) entry ="0";

        else if (e.getSource()==equals) {
            result = entry.replace('÷','/');
            result = result.replace('×','*');
            expression = new Expression(result);
            try {
                calculation = expression.evaluate();
            } catch (EvaluationException | ParseException ex) { // null pointer exception?
                System.out.println("something went wrong");
            }
            //guess: it doesn't calculate anything so result is empty right before the following are executed
            result = calculation.getStringValue();
            result = Helpers.rounder(result);
        }

        if (!entry.equals("0") && entry.charAt(0)=='0') entry = Helpers.cutFirstChar(entry);
        if (Helpers.isOperation(entry.charAt(0)) && entry.charAt(0)!='-') entry = "0" + entry;
        entryBox.setText(entry);

        if (!result.isEmpty()) {
            if (!result.equals("0") && result.charAt(0)=='0') {
                result = Helpers.cutFirstChar(result);
            }
            resultBox.setText(result);
        }



    }
}
