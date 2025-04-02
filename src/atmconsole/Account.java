package atmconsole;
import java.util.*;

import atmConsolesystem.ConsoleColors;

public class Account {
    private String userName;
    private String location;
    private String accountNum;
    private String pin;
    private double balance; 
    public static int accountCount = 0;
    public static Account[] accounts = new Account[100];
    public static String Admin_userName = "admin";
    public static String Admin_password = "admin123";

    public Account(String userName, String location, String accountNum, String pin, double balance) {
        this.userName = userName;
        this.location = location;
        this.accountNum = accountNum;
        this.pin = pin;
        this.balance = balance;
    }

    public static Scanner sc = new Scanner(System.in);

    public static void createAccount() {
        System.out.println(ConsoleColors.BLUE + "----------------Account Creation------------ " + ConsoleColors.RESET);
        System.out.print("Enter new UserName: ");
        String userName = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();
        System.out.print("Enter your Account Number: ");
        String accountNum = sc.nextLine();
        System.out.print("Enter your Pin: ");
        String pin = sc.nextLine();
        System.out.print("Enter your initial balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();
        accounts[accountCount++] = new Account(userName, location, accountNum, pin, balance);
        System.out.println(ConsoleColors.GREEN + "-------Account Created Successfully----------" + ConsoleColors.RESET);
    }

    public static void displayAccounts() {
        if (accountCount > 0) {
            System.out.println(ConsoleColors.BLUE+"-----List of Accounts------"+ConsoleColors.RESET);
            for (int i = 0; i < accountCount; i++) {
                if (accounts[i] != null) {
                    System.out.println("Account Number: " + accounts[i].accountNum + " | Name: " + accounts[i].userName + " | Location: " + accounts[i].location);
                }
            }
        } else {
            System.out.println(ConsoleColors.RED+"No accounts are present"+ConsoleColors.RESET);
        }
    }

    public static void adminLogin() {
        System.out.print("Enter username: ");
        String adminUserName = sc.nextLine();
        System.out.print("Enter password: ");
        String adminPassword = sc.nextLine();
        if (adminUserName.equals(Admin_userName) && adminPassword.equals(Admin_password)) {
            System.out.println(ConsoleColors.GREEN+"Admin successfully logged in"+ConsoleColors.RESET);
            while (true) {
                System.out.println("1. View all Accounts");
                System.out.println("2. Delete account");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int c = sc.nextInt();
                sc.nextLine();
                switch (c) {
                    case 1:
                        displayAccounts();
                        break;
                    case 2:
                        if (accountCount > 0) {
                            displayAccounts();
                            System.out.print("Enter Account Number to delete: ");
                            String accNum = sc.nextLine();
                            deleteAccount(accNum);
                        } else {
                            System.out.println(ConsoleColors.YELLOW+"No accounts found"+ConsoleColors.RESET);
                        }
                        break;
                    case 3:
                        System.out.println(ConsoleColors.YELLOW+"Logging OUT...."+ConsoleColors.RESET);
                        return;
                    default:
                        System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
                }
            }
        } else {
            System.out.println(ConsoleColors.RED+"Invalid Credentials"+ConsoleColors.RESET);
        }
    }

    public static void accountLogin() {
    	System.out.println(ConsoleColors.BLUE+"\n --------User Login--------\n"+ConsoleColors.RESET);
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].accountNum.equals(accNum) && accounts[i].pin.equals(pin)) {
                System.out.println(ConsoleColors.GREEN + "Login successful! Welcome " + accounts[i].userName + ConsoleColors.RESET);
                accountOperations(accounts[i]);
                return;
            }
        }
        System.out.println(ConsoleColors.RED + "Invalid Account Number or PIN!" + ConsoleColors.RESET);
    }
    
    public static void accountOperations(Account account) {
        while (true) {
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw (Multiples of 100): ");
                    int withdraw = sc.nextInt();
                    if (withdraw % 100 == 0 && withdraw <= account.balance) {
                        account.balance -= withdraw;
                        System.out.println(ConsoleColors.GREEN + "Withdrawal successful!" + ConsoleColors.RESET);
                    } else {
                        System.out.println(ConsoleColors.RED + "Invalid amount or insufficient balance!" + ConsoleColors.RESET);
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to deposit (Multiples of 100): ");
                    int deposit = sc.nextInt();
                    if (deposit % 100 == 0) {
                        account.balance += deposit;
                        System.out.println(ConsoleColors.GREEN + "Deposit successful!" + ConsoleColors.RESET);
                    } else {
                        System.out.println(ConsoleColors.RED + "Invalid deposit amount!" + ConsoleColors.RESET);
                    }
                    break;
                case 3:
                    System.out.println("Current Balance: " + account.balance);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
            }
        }
    }
    
    public static void deleteAccount(String accountNum) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].accountNum.equals(accountNum)) {
                accounts[i] = accounts[accountCount - 1];
                accounts[accountCount - 1] = null;
                accountCount--;
                System.out.println(ConsoleColors.GREEN + "Account deleted successfully!" + ConsoleColors.RESET);
                return;
            }
        }
        System.out.println(ConsoleColors.RED + "Account not found!" + ConsoleColors.RESET);
    }


    public static void main(String[] args) {
        while (true) {
            System.out.println(ConsoleColors.BLUE + "Welcome to the ATM System..!" + ConsoleColors.RESET);
            System.out.println("1. Create Account");
            System.out.println("2. Admin Login");
            System.out.println("3. Account Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    adminLogin();
                    break;
                case 3:
                    accountLogin();
                    break;
                case 4:
                    System.out.println(ConsoleColors.YELLOW + "Exiting the system" + ConsoleColors.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice" + ConsoleColors.RESET);
            }
        }
    }
}