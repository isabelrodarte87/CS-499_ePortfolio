package bank2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Launch implements ActionListener {
		
		private static JLabel label, label2, label3, label4;
		private static JTextField  userText, userText2, userText3, userText4;
		private static JButton button;

		public static void main(String[] args) {
			
			//Creates Frame and Panel 
			JPanel panel = new JPanel ();
			JFrame frame = new JFrame("Airgead Bank");
			frame.setSize(400, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// adds the panel
			frame.add(panel);
			
			//sets layout and background color
			panel.setLayout(null);
			panel.setBackground(Color.LIGHT_GRAY);
			
			//creates, sets, and adds Initial investment label
			label = new JLabel("Initial Investment: $ ");
			label.setBounds(10, 20, 120, 25);
			panel.add(label);
			
			//creates and sets and adds text box for initial investment
			userText = new JTextField(20);
			userText.setBounds(140, 20, 150, 25);
			panel.add(userText);
			
			//creates, sets, and adds Monthly deposit label
			label2 = new JLabel ("Monthly Deposit: $ ");
			label2.setBounds(10, 70, 200, 25);
			panel.add(label2);
			
			//creates and sets and adds text box for monthly deposit
			userText2 = new JTextField(20);
			userText2.setBounds(140, 70, 150, 25);
			panel.add(userText2);
			
			//creates, sets, and adds interest rate label
			label3 = new JLabel ("Interest Rate: % ");
			label3.setBounds(10, 120, 200, 25);
			panel.add(label3);
			
			//creates, sets, and adds text box for interest rate 
			userText3 = new JTextField(20);
			userText3.setBounds(140, 120, 150, 25);
			panel.add(userText3);
			
			//creates, sets, and adds number of years label
			label4 = new JLabel ("Number of Years: % ");
			label4.setBounds(10, 170, 200, 25);
			panel.add(label4);
			
			//creates, sets, and adds text box for number of years
			userText4 = new JTextField(20);
			userText4.setBounds(140, 170, 150, 25);
			panel.add(userText4);
			
			//creates, sets, and adds button to the bottom of the window
			button = new JButton ("Calculate");
			button.setBounds(100, 250, 200, 25);
			button.addActionListener(new Launch());
			panel.add(button);
			
			// creates success 
			JLabel success = new JLabel ("");
			success.setBounds(10,110, 300,25);
			panel.add(success);
			
			//lets window be seen
			frame.setVisible(true);
		}

		@Override
		//function of the calculation button
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button) {
				NewWindow mywindow = new NewWindow();
			}
			
		
			
		}

	}


