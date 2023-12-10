import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Java program for financial calculations and configurations.
 * This program includes a new window class, 'newWindow,' with enhanced feature
 * to display detailed financial information, including balances and interest.
 * 
 * The enhancement focus on improving code structure, readability, and adherence to Java conventions.
 *
 * @author Isabel Rodarte
 *
 */
public class FinancialDetailsWindow {

    // Create new window with components
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label = new JLabel(" Airgead Bank");
    JButton backButton;
    static JTextArea detailsTextArea;
    JScrollPane textAreaWithScroll;
    
    /**
     * Constructor for newWindow.
     * Initializes window components and sets up the ActionListener for the 'Go Back' button.
     */
    FinancialDetailsWindow() {

        // Sets size, visibility and exit behavior of the window
        frame.setSize(450, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel
        frame.add(panel);

        // Set layout and background color
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);

        // Create text area
        detailsTextArea = new JTextArea();
        // Set color of text area
        detailsTextArea.setBackground(Color.LIGHT_GRAY);
        // Create scroll pane for the details text area
        textAreaWithScroll = new JScrollPane(detailsTextArea);

        // Set the bounds for the scroll pane
        textAreaWithScroll.setBounds(50, 60, 350, 500);
        // Add scroll pane to the panel
        panel.add(textAreaWithScroll); 

        // Create a JButton for going back to the Launch screen
        backButton = new JButton("Go Back");
        // Set the bounds for the back button
        backButton.setBounds(300, 25, 100, 30);
        // Add button to panel
        panel.add(backButton);
        
        // Set visibility of the window
        frame.setVisible(true);

        // ActionListener for the backButton
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close NewWindow and open Launch window
                if (e.getSource() == backButton) {
                    frame.dispose();
                    UserInputWindow.main(new String[]{});
                }
            }
        });

    }
    
    
    /**
     *  Method to append financial details to the newWindow.
     *  
     * @param year             The current year.
     * @param yearEndBalance   The year-end balance.
     * @param interestEarned   The interest earned during the year.
     */
    public void appendDetails(int year, double yearEndBalance, double interestEarned) {
        // Creating an object of DecimalFormat to get 2 digits after decimal points
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        // Append details
        String details = String.format("%-18d $%-32s $%s\n", year,
                decimalFormat.format(yearEndBalance), decimalFormat.format(interestEarned));
        detailsTextArea.append("  " + details);
        detailsTextArea.append("\n");
    }
    
    /**
     *  Method to get detailsTextArea field.
     *  
     * @return JTextArea used for displaying financial details
     */
    public static JTextArea getDetailsTextArea(){
        return detailsTextArea;
    }
}