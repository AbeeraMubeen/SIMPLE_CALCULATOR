import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField expressionField;
    JTextField resultField;
    String input = "", op = "";
    double result = 0;

    public Calculator() {
        setTitle("Calculator");
        setSize(350, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set icon
        ImageIcon icon = new ImageIcon("my_icon.png");
        setIconImage(icon.getImage());

        // Top display panel
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(2, 1));
        displayPanel.setBackground(new Color(255, 240, 250)); // soft rose

        // Expression field (white)
        expressionField = new JTextField();
        expressionField.setFont(new Font("Arial", Font.PLAIN, 20));
        expressionField.setEditable(false);
        expressionField.setBackground(Color.WHITE);
        expressionField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(expressionField);

        // Result field (lavender)
        resultField = new JTextField();
        resultField.setFont(new Font("Arial", Font.BOLD, 26));
        resultField.setEditable(false);
        resultField.setBackground(new Color(230, 224, 250)); // light lavender
        resultField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(resultField);

        add(displayPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBackground(new Color(255, 240, 250));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setBackground(new Color(255, 182, 193)); // princess pink
            btn.setFocusable(false);
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String b = e.getActionCommand();

        if (b.equals("C")) {
            input = "";
            op = "";
            result = 0;
            expressionField.setText("");
            resultField.setText("");
        } else if (b.equals("=")) {
            try {
                String[] parts = input.split("[" + "\\" + op + "]");
                if (parts.length == 2) {
                    double num1 = Double.parseDouble(parts[0]);
                    double num2 = Double.parseDouble(parts[1]);

                    if (op.equals("+"))
                        result = num1 + num2;
                    else if (op.equals("-"))
                        result = num1 - num2;
                    else if (op.equals("*"))
                        result = num1 * num2;
                    else if (op.equals("/")) {
                        if (num2 == 0) {
                            resultField.setText("Can't divide by 0");
                            expressionField.setText("");
                            input = "";
                            return;
                        } else {
                            result = num1 / num2;
                        }
                    }

                    resultField.setText(String.valueOf(result));
                    expressionField.setText("");
                    input = String.valueOf(result);
                }
            } catch (Exception ex) {
                resultField.setText("Error");
                expressionField.setText("");
                input = "";
            }
        } else if (b.equals("+") || b.equals("-") || b.equals("*") || b.equals("/")) {
            if (!input.contains("+") && !input.contains("-") && !input.contains("*") && !input.contains("/")) {
                input += b;
                op = b;
                expressionField.setText(input);
            }
        } else {
            input += b;
            expressionField.setText(input);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}