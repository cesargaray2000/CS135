package edu.gcu.bootcamp.cst135.milestone.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.gcu.bootcamp.cst135.milestone.model.Checking;
import edu.gcu.bootcamp.cst135.milestone.model.Customer;
import edu.gcu.bootcamp.cst135.milestone.model.Saving;
import edu.gcu.bootcamp.cst135.milestone.model.Transaction;

public class Bank {
	private Customer customer;
	
	Boolean loggedIn = false;
	public Bank() throws ParseException {
		super();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date yourDate = df.parse("2018-05-30");
		customer = new Customer("Cesar","Garay", "cesargaray2000","password", yourDate);
//		customer = new Customer("Cesar","Garay", "a","a", yourDate);
	}
	
	Scanner scanner = new Scanner(System.in);
//	public Saving saving = new Saving("-SAV12345", 5000, 200, 25, .06);
//	public Checking checking = new Checking("-CHK23456", 5000, 25);
	
	
	

	private void viewExitScreen() {
		System.out.println("Goodbye, Have a good day!");
	}

	public void viewCustomerMenu() {
		int option;
		do {
			if(loggedIn) {
			System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("                MAIN MENU");
			System.out.println("                GCU BANK");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("Pick an option: ");
			System.out.println("-----------------------");
			System.out.println(" 1: Deposit to Checking");
			System.out.println(" 2: Deposit to Savings");
			System.out.println(" 3: Withdraw from Checking");
			System.out.println(" 4: Withdraw from Savings");
			System.out.println(" 5: Get balance");
			System.out.println(" 6: Get monthly statement");
			System.out.println("------------------------");
			System.out.println(" 9: : Logout");
			} else {
				System.out.println("===============");
				System.out.println("Main Menu");
				System.out.println("===============");
				System.out.println(" 1: log-in");
				System.out.println("9: Exit Bank");
			}
			option = getUserMenuChoice();
			 if (loggedIn) {
			processCustomerMenu(option);
			 }else {
				 if (option == 1) {
					 Login();
				 }else if (option == 9) {
					 viewExistScreen();
				 }
			 
			 }
		} while (option != 9);
	}

	private void Login() {
		System.out.println("Enter Username: ");
		String username = scanner.nextLine();
		System.out.println("\nEnter Password: ");
		String password = scanner.nextLine();
		
		if (customer.getUserName().equals(username) && customer.getPassword().equals(password)) {
			System.out.println("Welcome" + customer);
			
			System.out.println("You are Logged in");
			loggedIn = true;
		} else {
			System.out.println("Incorrect username/password combination");
		}
	}
	private void viewExistScreen() {
		// TODO Auto-generated method stub
		System.out.println("Exit Screen");
	}



	private int getUserMenuChoice() {
		boolean success = false;
		int result = 0;
		while (!success) {
			String option = scanner.nextLine();
			success = true;
			try {
				result = Integer.parseInt(option);
			} catch (NumberFormatException e) {
				System.out.println("Expecting a numeric menu choice.  Please try again.");
				success = false;
			}
		}
		return result;
	}

	private double getUserAmount() {
		boolean success = false;
		double result = 0;
		while (!success) {
			String option = scanner.nextLine();
			success = true;
			try {
				result = Double.parseDouble(option);
				
			} catch (NumberFormatException e) {
				System.out.println("Expecting a dollar amount.  Please try again.");
				success = false;
			}
		}
		return result;
	}
ArrayList<Transaction> listOfTrans = new ArrayList<Transaction>();
	
	private void addTransaction(Transaction trans) {
	listOfTrans.add(trans);
	}

	private void processCustomerMenu(int parseInt) {
		switch (parseInt) {
		case 1:
			viewDepositChecking();
			viewBalances();
			break;
		case 2:
			viewDepositSavings();
			viewBalances();
			break;
		case 3:
			viewWithdrawalChecking();
			viewBalances();
			break;
		case 4:
			viewWithdrawalSavings();
			viewBalances();
			break;
		case 5:
			viewBalances();
			break;
		case 6:
			viewEndOfMonth();
			viewBalances();
			break;
		case 9:
			viewExitScreen();
			break;
		default:
			viewCustomerMenu();
		}
	}

	private void viewEndOfMonth() {

		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("               END OF MONTH");
		System.out.println("                 GCU BANK");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");

		if (customer.getSaving().getAccountBalance() < customer.getSaving().getMinBalance()) {
			System.out.printf("A $%.2f service fee is being assessed for below minimum balance in savings",
					customer.getSaving().getServiceFee());
			customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() - customer.getSaving().getServiceFee());
		}
		if (customer.getSaving().getAccountBalance() > 0) {
			customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() + (customer.getSaving().getInterest() * customer.getSaving().getAccountBalance()));
			addTransaction(new Transaction(customer.getSaving().getAccountNumber(),customer.getSaving().getInterest(), "Interest accrued" ));
		}
	displayTransactions();
	}
	
	public void displayTransactions() {
        System.out.println("\n-----------SESSION TRANSACTIONS------------");    
        for(int i = 0; i < listOfTrans.size(); i++){
            System.out.println(listOfTrans.get(i));
        }
        System.out.println("-------------------------------------------");
    }
	private void viewWithdrawalChecking() {
				System.out.println("How much would you like to withdraw: ");
			double input = getUserAmount();

			System.out.println(input);
			processWithdrawalChecking(input);
			addTransaction(new Transaction(customer.getChecking().getAccountNumber(),input, "Withdraw" ));
			
		}
			

	private void processWithdrawalChecking(double input) {
		

		if (customer.getChecking().getAccountBalance() < input) {
			System.out.println("A $" + customer.getChecking().getOverdraft()
					+ " overdraft fee will be assessed if you continue. Continue Y or N?");
			if (scanner.nextLine().toLowerCase().equals("y")) {
				customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() - input - customer.getChecking().getOverdraft());
				addTransaction(new Transaction(customer.getChecking().getAccountNumber(),customer.getChecking().getOverdraft(), "Withdraw" ));
			} else
				viewWithdrawalChecking();
		} else
			customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() - input);
	
	}

	private void viewDepositSavings() {
			System.out.println("How much would you like to deposit: ");
			double input = getUserAmount();
			processDepositSavings(input);
			addTransaction(new Transaction(customer.getSaving().getAccountNumber(), input, "Deposit" ));
		}

	private void processDepositSavings(double input) {

		customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() + input);
		addTransaction(new Transaction(customer.getSaving().getAccountNumber(), input, "Deposit" ));
	}

	private void viewDepositChecking() {

	
			System.out.println("How much would you like to deposit: ");
			double input = getUserAmount();
			processDepositChecking(input);
			addTransaction(new Transaction(customer.getChecking().getAccountNumber(), input, "Deposit" ));
		}


	private void processDepositChecking(double input) {

		customer.getChecking().setAccountBalance(customer.getChecking().getAccountBalance() + input);
	}

	private void viewWithdrawalSavings() {

	
			System.out.println("How much would you like to withdraw: ");
			double input = getUserAmount();
			processWithdrawalSavings(input);
			addTransaction(new Transaction(customer.getSaving().getAccountNumber(), input, "Withdraw" ));
		}

	private void processWithdrawalSavings(double input) {

		customer.getSaving().setAccountBalance(customer.getSaving().getAccountBalance() - input);
	}

	private void viewBalances() {

		System.out.println("\n------------------------");
		System.out.println(customer.getSaving().toString());
		System.out.println(customer.getChecking().toString());
		System.out.println(customer.getLoan().toString());
		System.out.println("------------------------");
	}
}