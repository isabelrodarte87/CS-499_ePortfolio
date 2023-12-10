package bank2;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Java program for financial calculations and configurations.
 * This program includes a new window class, 'newWindow,' with enhanced feature
 * to display detailed financial information, including balances and interest.
 * 
 * The enhancement focus on creating a new window that will house financial calculations.
 *
 * @author Isabel Rodarte
 */

public class FinancialDetailsWindow {

	//create new window with compnonets
	JFrame frame = new JFrame();
	JPanel panel = new JPanel ();
	JLabel label = new JLabel(" Airgead Bank");
	
    /**
     * Constructor for newWindow.
     * Initializes window components.
     */
	
	FinancialDetailsWindow(){
		
        // Sets size, visibility and exit behavior of the window
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// adds the panel
		frame.add(panel);
		
		//sets layout and background color
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		
		//creates, sets, and adds label
				label = new JLabel("-----Without Deposits-----");
				label.setBounds(250, 20, 120, 25);
				panel.add(label);
				
		//creates, sets, and adds label
				label = new JLabel("-----With Deposits-----");
				label.setBounds(260, 300, 120, 25);
				panel.add(label);
					
}
}