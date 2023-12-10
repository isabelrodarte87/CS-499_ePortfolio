package bank2;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewWindow {

	//create new window
	JFrame frame = new JFrame();
	JPanel panel = new JPanel ();
	JLabel label = new JLabel(" Airgead Bank");
	
	NewWindow(){
		
		// sets size, visibility and exit of window
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