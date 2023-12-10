package bank2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Java program for user to input financial calculations.
 * This program allows users to input initial investment, monthly deposit, interest rate, and number of years.
 * It calculates and displays financial details using the newWindow class.
 * Enhancements includes improved code structure and enhancing the overall design.
 * 
 * @author Isabel Rodarte
 *
 */
public class UserInputWindow implements ActionListener {

    private static JLabel label, label2, label3, label4;
    private static JTextField userText, userText2, userText3, userText4;
    private static JButton button;
    private static JFrame frame;
    
    public static void main(String[] args) {
    	
        // Create initial window with components
        JPanel panel = new JPanel();
        frame = new JFrame("Airgead Bank");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel
        frame.add(panel);

        // Set layout and background color
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);

        // Create, set, and add Initial investment label
        label = new JLabel("Initial Investment: $ ");
        label.setBounds(10, 20, 120, 25);
        panel.add(label);

        // Create, set, and add text box for initial investment
        userText = new JTextField(20);
        userText.setBounds(140, 20, 150, 25);
        panel.add(userText);

        // Create, set, and add Monthly deposit label
        label2 = new JLabel("Monthly Deposit: $ ");
        label2.setBounds(10, 70, 200, 25);
        panel.add(label2);

        // Create and set and add text box for monthly deposit
        userText2 = new JTextField(20);
        userText2.setBounds(140, 70, 150, 25);
        panel.add(userText2);

        // Create, set, and add interest rate label
        label3 = new JLabel("Interest Rate: % ");
        label3.setBounds(10, 120, 200, 25);
        panel.add(label3);

        // Create, set, and add text box for interest rate 
        userText3 = new JTextField(20);
        userText3.setBounds(140, 120, 150, 25);
        panel.add(userText3);

        // Create, set, and add number of years label
        label4 = new JLabel("Number of Years:   ");
        label4.setBounds(10, 170, 200, 25);
        panel.add(label4);

        // Create, set, and adds text box for number of years
        userText4 = new JTextField(20);
        userText4.setBounds(140, 170, 150, 25);
        panel.add(userText4);

        // Create, set, and add button to the bottom of the window
        button = new JButton("Calculate");
        button.setBounds(100, 250, 200, 25);
        button.addActionListener(new UserInputWindow());
        panel.add(button);

        // Create a success label
        JLabel success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);
        
        // Set visibility of the window
        frame.setVisible(true);
        
        
    }


		@Override
		//Method of the calculation button
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				FinancialDetailsWindow mywindow = new FinancialDetailsWindow();
			}
			
		
			
		}

	}


