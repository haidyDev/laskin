import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Laskin {

    private JFrame frame;
    private JTextField display;

    private double firstNumber = 0;
    private String operator = "";

    public Laskin() {
        frame = new JFrame("Laskin");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(e.getActionCommand());
                }
            });
            buttonPanel.add(btn);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void handleButtonClick(String buttonValue) {
        if (buttonValue.matches("[0-9]+") || buttonValue.equals(".")) {
            display.setText(display.getText() + buttonValue);
        } else if (buttonValue.equals("=")) {
            calculateResult();
        } else {
            if (!operator.isEmpty()) {
                calculateResult();
            }
            firstNumber = Double.parseDouble(display.getText());
            operator = buttonValue;
            display.setText("");
        }
    }

    private void calculateResult() {
        double result = 0;
        double secondNumber = Double.parseDouble(display.getText());
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    display.setText("Virhe: Ei voida jakaa nollalla!");
                    return;
                }
                break;
        }
        display.setText(String.valueOf(result));
        firstNumber = result;
        operator = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Laskin();
            }
        });
    }
}
