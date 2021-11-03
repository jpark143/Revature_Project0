package com.company;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        CustomerDao daoc = CustomerDaoFactory.getCustomerDao(); // added CustomerDao file to insert the lists of methods I want to implement
        EmployeeDao daoe = EmployeeDaoFactory.getEmployeeDao(); // added EmployeeDao file
        AccountDao daoa = AccountDaoFactory.getAccountDao();    // added AccountDao file
        TransactionDao daot = TransactionDaoFactory.getTransactionDao(); // added TransactionDao file
        Scanner scanner = new Scanner(System.in); // scanner to take input from user
        Employee employee = new Employee(); // set employee

        boolean mainM = true; // start the main menu condition.
        while (mainM){ // while loop menu.
            System.out.println("*----------------------*"); // display main menu.
            System.out.println("  Welcome to bank Java ");
            System.out.println("*----------------------*");
            System.out.println("Select from the options:");
            System.out.println("-----------------------");
            System.out.println("Press 1: Open account");
            System.out.println("PRESS 2: Customer login");
            System.out.println("PRESS 3: Employee login");
            System.out.println("PRESS 4: Exit");
            System.out.println("-----------------------");

            int input1 = scanner.nextInt();
            switch (input1){ // take user input for multiple cases.
                case 1: {
                    // start to create account for user
                    System.out.println("*------------------------------------*");
                    System.out.println(" Welcome!!! Lets set up your account!");
                    System.out.println("*------------------------------------*");
                    System.out.println("Set up your Username: ");
                    String username = scanner.next();
                    System.out.println("Set up your Password: ");
                    String password = scanner.next();
                    System.out.println("Enter your email: ");
                    String email = scanner.next();
                    System.out.println("Enter starting balance: ");
                    double balance = scanner.nextDouble();
                    if (balance > 0 ){ // stop user when they enter negative number.
                        Customer customer = new Customer();
                        customer.setUsername(username);
                        customer.setPassword(password);
                        customer.setEmail(email);
                        daoc.addCustomer(customer);
                        Account account = new Account();
                        account.setBalance(balance);
                        daoa.addAccount(account);

                        System.out.println("-----------------------------------------------");
                        System.out.println(" Please wait until your account gets approve...");
                        System.out.println("-----------------------------------------------");
                        break;
                    }else
                    // kick user out when they enter negative number
                    System.out.println("Invalid input!! Going Back...");
                    System.out.println(" ");
                }
                break;

                case 2: {
                    // customer login section
                    System.out.println("*------------------------------*");
                    System.out.println(" Welcome, Customer! Lets login ");
                    System.out.println("*------------------------------*");
                    System.out.println("Enter your username: ");
                    String username = scanner.next();
                    System.out.println("Enter your password: ");
                    String password = scanner.next();
                    Customer customer = new Customer();
                    customer.getUsername(); // get username
                    customer.getPassword(); // get user password
                    daoc.getCustomer(username, password); // allows user to login

                    boolean custL = true;
                    while (custL){
                        // menu for the account holders
                        System.out.println("*---------------------------*");
                        System.out.println("    Select your option");
                        System.out.println("*---------------------------*");
                        System.out.println("Press 1: View your balance");
                        System.out.println("Press 2: Deposit");
                        System.out.println("Press 3: Withdraw");
                        System.out.println("Press 4: Transfer");
                        System.out.println("Press 5: Return to main menu");
                        System.out.println("-----------------------------");
                        int cust_input = scanner.nextInt();

                        switch (cust_input) {
                            case 1: {
                                // allow account holders to view current balance
                                System.out.println("To view your current balance,");
                                System.out.println("Lets verify your account Number: ");
                                int accNum = scanner.nextInt();
                                Account account = daoa.getBalance(accNum);
                                System.out.println("Current balance for account " + account.getAccountNum() + " is: $" +
                                        account.getBalance() + " in your account." + "\n");
                                break;
                            }
                            case 2: {
                                // allow account holders to deposit to their account
                                System.out.println("Lets verify your account Number: ");
                                int accNum = scanner.nextInt();
                                System.out.println("Enter amount you want to deposit: ");
                                double dep = scanner.nextDouble();
                                // call the accountNum and find the account correct and add amount to the balance
                                Account account = new Account();
                                account.setAmount(dep);
                                account.setAccountNum(accNum);
                                while (dep > 0) {
                                    daoa.updateAmount(account);
                                    daoa.addBalance(accNum, dep);
                                    account = daoa.getBalance(accNum);
                                    System.out.println("Your New balance is: $" + account.getBalance() + "\n");
                                    break;
                                }
                                if (dep < 0){
                                    // stop account holders from entering negative input
                                    System.out.println("Error" +"\n");
                                }
                                break;

                            }
                            case 3:{
                                // allow account holders to withdraw from their account
                                System.out.println("Lets verify your account Number: ");
                                int accNum = scanner.nextInt();
                                System.out.println("Enter amount you want to withdraw: ");
                                double wtd = scanner.nextDouble();
                                Account account = new Account();
                                account= daoa.getBalance(accNum);
                                while (wtd < account.getBalance()){
                                    account.setAmount(wtd);
                                    account.setAccountNum(accNum);

                                    while (wtd > 0){
                                        daoa.updateAmount(account);
                                        daoa.withdraw(accNum, wtd);
                                        account = daoa.getBalance(accNum);
                                        System.out.println("Your New balance is: $" + account.getBalance() + "\n");
                                        break;
                                    }
                                    break;
                                }
                                if (wtd < 0){
                                    // stop account holders from enter negative input
                                    System.out.println("Error" + "\n");
                                }
                                else if (wtd > account.getBalance()){
                                    System.out.println("Error" + "\n");
                                }
                                break;
                            }
                            case 4:{
                                // allow account holders to transfer to another account
                                // ask customer where do they want to make transfer from
                                System.out.println("Transfer Section");
                                System.out.println("Enter your account number you want to make transfer from.");
                                int tranFromId = scanner.nextInt();
                                System.out.println("Enter a account number you want to make transfer to.");
                                int tranToId = scanner.nextInt();
                                if (tranFromId == tranToId){ // stop account holders to set same account number
                                    System.out.println("Invalid process..." +"\n");
                                    break;
                                }
                                else
                                    System.out.println("Enter the amount you want to transfer: ");
                                    double tranAmount = scanner.nextDouble();
                                    Account account = new Account();
                                    account = daoa.getBalance(tranFromId);
                                    while (tranAmount < account.getBalance() && tranAmount > 0){ // stop transfer if transfer amount is bigger
                                        account.setAmount(tranAmount);
                                        account.setAccountNum(tranFromId);
                                        daoa.withdraw(tranFromId, tranAmount);
                                        account.setAccountNum(tranToId);
                                        daoa.updateAmount(account);
                                        daoa.addBalance(tranToId, tranAmount);

                                        Transaction transaction = new Transaction();
                                        transaction.setAcc_from(tranFromId);
                                        transaction.setAcc_to(tranToId);
                                        transaction.setAmount(tranAmount);
                                        daot.addTransaction(transaction);
                                        break;
                                    }
                                    if (tranAmount > account.getBalance()) {
                                        System.out.println("Error..." + "\n");
                                    }
                                    else if (tranAmount < 0){
                                        System.out.println("Error.." + "\n");
                                    }
                                break;
                            }

                            case 5: {
                                // return to main menu
                                System.out.println("Going back...");
                                custL = false;
                                break;
                            }
                            default:
                                // tell user the input is invalid.
                                System.out.println("NOPE! wrong input");
                        }
                    }

                }
                break;

                case 3:{
                    // allow employee to login
                    System.out.println("*-----------------------------*");
                    System.out.println(" Welcome, Employee! Lets login");
                    System.out.println("*-----------------------------*");
                    System.out.println("Enter your username: ");
                    String empName = scanner.next();
                    System.out.println("Enter your password:");
                    String empPassword = scanner.next();
                    employee.getEmpName();
                    employee.getEmpPassword();
                    daoe.getEmployee(empName, empPassword);

                    boolean empL = true;
                    while (empL){
                        System.out.println("*--------------------------------------*");
                        System.out.println("         Select your option:");
                        System.out.println("*--------------------------------------*");
                        System.out.println("Press 1: View all customers");
                        System.out.println("Press 2: Approve or reject new customer");
                        System.out.println("Press 3: View customers bank account");
                        System.out.println("Press 4: View log of all transaction");
                        System.out.println("Press 5: Go back to main menu");
                        System.out.println("----------------------------------------");
                        int emp_input = scanner.nextInt();
                        switch (emp_input){
                            case 1:{
                                // allow employee to view all customers
                                System.out.println("List of all customers: ");
                                List<Customer> customers = daoc.getAllCustomer();
                                for(Customer customer: customers){
                                    System.out.println("ID: " + customer.getId() + ", Username: " + customer.getUsername() +
                                            ", Email: " + customer.getEmail() + " Status: " + customer.getStatus());
                                }
                                break;
                            }
                            case 2:{
                                // To approve or reject new customer account
                                // List out all pending customers
                                System.out.println("List of customers that on pending: ");
                                List<Customer> customers = daoc.getStatus();
                                for (Customer customer: customers){
                                    System.out.println("ID: " + customer.getId() + ", Username: " + customer.getUsername() +
                                            ", Email: " + customer.getEmail() + ", Status: " + customer.getStatus());
                                }
                                System.out.println("---------------------------------------------");
                                // select customer that are on pending
                                System.out.println("Enter Customer ID that you want to change the status: ");
                                int id = scanner.nextInt();
                                // allow employee to decide
                                System.out.println("Status 0 = reject, Status 1 = approve");
                                int status = scanner.nextInt();
                                Customer customer = new Customer();
                                customer.setId(id);
                                customer.setStatus(status);
                                daoc.updateStatus(customer);
                                if (status == 0){
                                    daoc.deleteCustomer(id);
                                    daoa.deleteAccount(id);
                                }
                                else
                                    System.out.println("Its all done!");
                                break;
                            }
                            case 3:{
                                // allow employee to view customers bank account by id
                                System.out.println("Enter customer ID to see a customers account: ");
                                int id = scanner.nextInt();
                                Customer customer = daoc.getCustomerById(id);
                                System.out.println("You Entered " + id + ":");
                                System.out.println("ID: " + customer.getId() + ", Username: " + customer.getUsername() +
                                        ", Email: " + customer.getEmail() + ", Status: " + customer.getStatus());
                                break;
                            }
                            case 4:{
                                // allow employee to view log of all transactions
                                System.out.println("All transactions: ");
                                List<Transaction> transactions = daot.getAllTransaction();
                                for (Transaction transaction: transactions){
                                    System.out.println("Transaction ID: " + transaction.getTrans_id() + ", Account From: " +
                                            transaction.getAcc_from() + ", Account To: " + transaction.getAcc_to() +
                                            ", Amount: " + transaction.getAmount());
                                }
                                break;
                            }
                            case 5:{
                                // go back to main menu
                                System.out.println("Going back...");
                                empL = false;
                                break;
                            }
                        }
                    }break;
                }
                case 4:{
                    // if user wants to end the program
                    System.out.println("Exiting the bank...");
                    System.out.println("Bye Bye!");
                    mainM = false;
                    break;
                }
                default:
                    // tell user that the input is not valid
                    System.out.println("NOPE! Wrong input!!!!");
            }

        }

    }
}
