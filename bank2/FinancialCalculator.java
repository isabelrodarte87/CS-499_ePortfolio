package bank2;
import java.util.Scanner;

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
	 * Methods to print financial details with proper formatting.
	 * 
	 * @param year              The current year.
	 * @param yearEndBalance    The year-end balance.
	 * @param interestEarned    The interest earned during the year.
	 * @param resultWindow      The GUI window to display results.
	 */

	    // method to print details with proper formatting
	    static void printDetails(int year, double yearEndBalance, double interestEarned) {
	        // Formatting output using printf
	        System.out.printf("%-18d $%-22.2f $%.2f\n", year, yearEndBalance, interestEarned);
	    }

	    // method to calculate balance without monthly deposit
	    static double calculateBalanceWithoutMonthlyDeposit(double initialInvestment, double interestRate, int numberOfYears) {
	        // Compounding should be done monthly
	        interestRate /= 100;
	      interestRate /=  12;
	        
	        // Loop through each year
	        for (int i = 1; i <= numberOfYears; i++) {
	          double currentInvestment = initialInvestment;
	          for(int j = 1; j <= 12; j++) {
	            currentInvestment += currentInvestment * interestRate;
	          }
	            double interestEarnedThisYear = currentInvestment - initialInvestment;
	            printDetails(i, currentInvestment, interestEarnedThisYear);
	            initialInvestment = currentInvestment;
	        }

	        // Return the final balance
	        return initialInvestment;
	    }

	    // Method to calculate balance with monthly deposit
	    static double balanceWithMonthlyDeposit(double initialInvestment, double monthlyDeposit, double interestRate, int numberOfYears) {
	        // Compounding should be done monthly
	      // Compounding should be done monthly
	      interestRate /= 100;
	      interestRate /=  12;

	        double currentInvestment = initialInvestment;

	        // Loop through each year
	        for (int i = 1; i <= numberOfYears; i++) {
	            // Loop through each month
	            for (int j = 1; j <= 12; j++) {
	                currentInvestment += currentInvestment * interestRate;
	                if (j == 12) {
	                    currentInvestment += monthlyDeposit;
	                }
	            }
	            
	            //Method to calculate compound interest
	            double interestEarnedThisYear = currentInvestment - monthlyDeposit * 12 - initialInvestment;
	            printDetails(i, currentInvestment, interestEarnedThisYear);
	            initialInvestment = currentInvestment;
	        }

	        // Return the ending balance
	        return initialInvestment;
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        double initialInvestment;
	        double monthlyDeposit;
	        double interestRate;
	        int numberOfYears = 5;

	        while (true) {
	            // Input
	        	System.out.println();
	            System.out.println("********************************************");
	            System.out.println("***************Data Input*******************");

	            System.out.print("Initial Investment Amount: $");
	            initialInvestment = scanner.nextDouble();

	            System.out.print("Monthly Deposit: $");
	            monthlyDeposit = scanner.nextDouble();

	            System.out.print("Annual Interest: %");
	            interestRate = scanner.nextDouble();

	            System.out.print("Number of years: ");
	            numberOfYears = scanner.nextInt();

	            System.out.print("Press Enter to continue...");
	            scanner.nextLine(); // consume the newline character

	            // Table header
	            System.out.println("=============================================================");
	            System.out.println("  Balance and Interest Without Additional Monthly Deposits");
	            System.out.println("=============================================================");
	            System.out.println("Year            Year End Balance    Year End Earned Interest");
	            System.out.println("-------------------------------------------------------------");
	            calculateBalanceWithoutMonthlyDeposit(initialInvestment, interestRate, numberOfYears);
	            System.out.println("-------------------------------------------------------------\n\n");

	            // Table header
	            System.out.println("=============================================================");
	            System.out.println("   Balance and Interest With Additional Monthly Deposits");
	            System.out.println("=============================================================");
	            System.out.println("Year            Year End Balance    Year End Earned Interest");
	            System.out.println("-------------------------------------------------------------");
	            balanceWithMonthlyDeposit(initialInvestment, monthlyDeposit, interestRate, numberOfYears);
	            System.out.println("-------------------------------------------------------------");

	            System.out.print("Press Enter to continue...");
	            scanner.nextLine(); // consume the newline character

	            System.out.print("Do you want to recalculate? Press (N/n) to end the program: ");
	            char choice = scanner.next().charAt(0);
	            if (choice == 'n' || choice == 'N') {
	                break;
	            }

	         // consume the newline character
	            scanner.nextLine(); 
	        }

	        // Close the scanner
	        scanner.close();
	    }
	}


