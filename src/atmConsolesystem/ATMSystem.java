package atmConsolesystem;
import java.util.*;
public class ATMSystem {
	private Account[] accounts = new Account[100];
	private int accountCount=0;
	private Scanner sc=new Scanner(System.in);
	
	private Admin admin = new Admin();
	private ATM atm = new ATM();
	
	public void start() {
		while(true) {
			System.out.println(ConsoleColors.BLUE+"\n========== Welcome to ATM System ==========\n"+ConsoleColors.RESET);
			System.out.println("1. Create Account\n2. Admin Login\n3. Account Login\n4. Exit");
			System.out.print("Enter your Choice: ");
			int choice = sc.nextInt(); sc.nextLine();
			switch(choice) {
			case 1: createAccount(); break;
			case 2: adminLogin(); break;
			case 3: accountLogin(); break;
			case 4: System.out.println(ConsoleColors.YELLOW+"\nExiting...\n"+ConsoleColors.RESET); return;
			default: System.out.println(ConsoleColors.RED+"\nInvalid Choice...!\n"+ConsoleColors.RESET);
			}
		}
	}
	//=======   Account creation   =================
	public void createAccount() {
		System.out.print("Enter your username: ");
		String userName = sc.nextLine();
		System.out.print("Enter Location: ");
		String location = sc.nextLine();
		System.out.print("Enter Account Number: ");
		String accountNum = sc.nextLine();
		System.out.print("Enter PIN: ");
		String pin  =sc.nextLine();
		System.out.print("Enter initial Balance: ");
		double balance = sc.nextDouble();
		sc.nextLine();
		  if (userName.isEmpty() || location.isEmpty() || accountNum.isEmpty() || pin.isEmpty() || balance < 0) {
		        System.out.println(ConsoleColors.RED + "Error: Account creation unsuccessful. Please enter valid details." + ConsoleColors.RESET);
		        return;
		    }
			accounts[accountCount++]=new Account(userName,location,accountNum,pin,balance);
			System.out.println(ConsoleColors.GREEN+"\n------Account created Successfully------\n"+ConsoleColors.RESET);
			
		
	}
	// ======= admin login ======
	public void adminLogin() {
		System.out.print("Enter username: ");
		String user = sc.nextLine();
		System.out.print("Enter Password: ");
		String pass =sc.nextLine();
		if(admin.login(user, pass)) {
			System.out.println(ConsoleColors.GREEN+"\n-------Admin Login Successfully-------\n"+ConsoleColors.RESET);
			while(true) {
				System.out.println("1. View All Accounts\n2. Delete Account\n3. LogOut");
				System.out.print("Enter your choice: ");
				int choice = sc.nextInt();sc.nextLine();
				switch(choice) {
				case 1: admin.viewAccounts(accounts,accountCount); break;
				case 2: if(accountCount>0) {
						admin.viewAccounts(accounts, accountCount);
						System.out.print("Enter Account number to Delete: ");
						String accNum = sc.nextLine();
						int count=admin.deleteAccount(accounts,accountCount,accNum);
						if(count!=accountCount) {
							accountCount=count;
						}
						}else {
							System.out.println(ConsoleColors.YELLOW+"\nNo Accounts to Delete..!\n"+ConsoleColors.RESET);
						}break;
				case 3: System.out.println(ConsoleColors.YELLOW+"\nLogging Out...!\n"+ConsoleColors.RESET);return;
				default:System.out.println(ConsoleColors.RED+"\nInValid Choice...!\n"+ConsoleColors.RESET);
				}
			}
		}else {
			System.out.println(ConsoleColors.RED+"\n Invalid Credientials"+ConsoleColors.RESET);
		}
	}
	//user login
	public void accountLogin() {
		System.out.print("Enter Account Number: ");
		String accNum = sc.nextLine();
		System.out.print("Enter Your Pin: ");
		String pin = sc.nextLine();
		Account account = atm.authenticate(accounts, accountCount, accNum, pin);
		if(account!=null) {
			System.out.println(ConsoleColors.GREEN+"Login Successfull "+account.getUserName()+ConsoleColors.RESET);
			while(true) {
				 System.out.println("1. Withdraw\n2. Deposit\n3. Check Balance\n4. Logout");
				 System.out.print("Enter your choice: ");
	             int choice = sc.nextInt(); sc.nextLine();
	             switch(choice) {
	             case 1: System.out.print("Enter Amount to Withdraw: ");
			             atm.withDraw(account, sc.nextDouble());
			             sc.nextLine(); break;
	             case 2:  System.out.print("Enter Amount to Deposit: ");
	             			atm.deposit(account, sc.nextDouble());
	             			sc.nextLine(); break;
	             case 3: atm.checkBalance(account); break;
	             case 4: System.out.println("Logging Out..!"); return;
	             default: System.out.println(ConsoleColors.RED+"Invalid choice..!"+ConsoleColors.RESET);
	             }
			}
		}else {
			System.out.println(ConsoleColors.RED+"Invalid account details"+ConsoleColors.RESET);
		}
	}
	public static void main(String[] args) {
		ATMSystem atmsys = new ATMSystem();
		atmsys.start();
	}
}
