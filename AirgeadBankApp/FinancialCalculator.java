/**
 * Java program for financial calculations and configurations.
 * This program includes methods to calculate the balances with and without monthly deposits
 * over a number of years with compound interest
 * 
 * The calculations are enhanced to use a more efficient algorithm and mathematical formula for accuracy.
 * Data mining is considered for future improvements
 * 
 * @author Isabel Rodarte
 */
public class FinancialCalculator {
	
	/**
	 * Functions to print financial details with proper formatting.
	 * 
	 * @param year              The current year.
	 * @param yearEndBalance    The year-end balance.
	 * @param interestEarned    The interest earned during the year.
	 * @param resultWindow      The GUI window to display results.
	 */

    static void printFinancialDetails(int year, double yearEndBalance, double interestEarned, FinancialDetailsWindow resultWindow) {
        // Append details to the new window
        resultWindow.appendDetails(year, yearEndBalance, interestEarned);
    }
    
    /**
     * Functions to calculate balances without monthly deposits.
     * 
     * @param initialInvestment   The initial investment amount
     * @param interestRate        The annual interest rate.
     * @param numberOfYears       The number of years to calculate.
     * @param resultWindow        The GUI window to display results
     * @return                    The final balance without monthly deposits.
     */

    static double calculateBalanceWithoutMonthlyDeposit(double initialInvestment, double interestRate, int numberOfYears, FinancialDetailsWindow resultWindow) {
        // Convert annual interest rate to monthly and adjust for compounding
    	double monthlyInterestRate = interestRate / (12 * 100);
        
        // Loop through each year
        for (int i = 1; i <= numberOfYears; i++) {
            // Append heading only in the first year
            if(i == 1){
                // call getDetailsTextArea method of ResultWindow class to append heading
                FinancialDetailsWindow.getDetailsTextArea().append("  Balance and Interest Without Additional Monthly Deposits\n\n");
                FinancialDetailsWindow.getDetailsTextArea().append("  Year            Year End Balance    Year End Earned Interest\n\n");
            }
            
            //use compound interest formula for efficient calculation
            double currentInvestment = initialInvestment * Math.pow(1 + monthlyInterestRate, 12 * i );
            double interestEarnedThisYear = currentInvestment - initialInvestment;
           
            // Append details to GUI window
            printFinancialDetails(i, currentInvestment, interestEarnedThisYear, resultWindow);
            initialInvestment = currentInvestment;
        }

        // Return the final balance
        return initialInvestment;
    }
    
    /**
     * Function to calculate balance with monthly deposit.
     * 
     * @param initialInvestment   The initial investment amount.
     * @param monthlyDeposit      The additional monthly deposit.
     * @param interestRate        The annual interest rate.
     * @param numberOfYears       The number of years to calculate.
     * @param resultWindow        The GUI window to display results
     * @return                    The final balance with monthly deposits
     */

    static double calculateBalanceWithMonthlyDeposit(double initialInvestment, double monthlyDeposit, double interestRate, int numberOfYears, FinancialDetailsWindow resultWindow) {
        // Convert annual interest rate to monthly and adjust for compounding
    	double monthlyInterestRate = interestRate / (12 * 100);

        // Loop through each year
        for (int i = 1; i <= numberOfYears; i++) {
            // Append heading only in the first year
            if(i == 1){
                // call getDetailsTextArea method of ResultWindow class to append heading 
                FinancialDetailsWindow.getDetailsTextArea().append("  Balance and Interest With Additional Monthly Deposits\n\n");
                FinancialDetailsWindow.getDetailsTextArea().append("  Year            Year End Balance    Year End Earned Interest\n\n");
            }
            
            // Use the compound interest formula for efficient calculation and consider monthly deposit
            double currentInvestment = initialInvestment * Math.pow(1 + monthlyInterestRate, 12 * i);
            currentInvestment += monthlyDeposit * ((Math.pow(1 + monthlyInterestRate, 12 * i) - 1) / monthlyInterestRate);

            double interestEarnedThisYear = currentInvestment - monthlyDeposit * 12 - initialInvestment;

            // Append details to GUI window
            printFinancialDetails(i, currentInvestment, interestEarnedThisYear, resultWindow);
            initialInvestment = currentInvestment;
        }

        // Return the final balance
        return initialInvestment;
    }
}