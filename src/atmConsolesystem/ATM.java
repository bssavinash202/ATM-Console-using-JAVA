package atmConsolesystem;

public class ATM {
	public Account authenticate(Account[] accounts, int accountCount, String accNum,String pin) {
		for(int i=0;i<accountCount;i++) {
			if(accounts[i]!=null&&accounts[i].getAccountNum().equals(accNum) && accounts[i].getPin().equals(pin)) {
				return accounts[i];
			}
		}
		return null;
	}
	public void withDraw(Account account, double amount) throws InvalidAmountException{
		if(amount%100==0 &&amount<=account.getBalance()) {
			account.setBalance(account.getBalance()-amount);
			System.out.println(ConsoleColors.GREEN+"\nWithdrawal Success\n"+ConsoleColors.RESET);
		}else {
			throw new InvalidAmountException(ConsoleColors.RED+"\nInvalid amount or insufficient balance!\n"+ConsoleColors.RESET);
		}
	}
	public void deposit(Account account, double amount) throws InvalidAmountException{
        if (amount % 100 == 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println(ConsoleColors.GREEN+"\nDeposit successful!\n"+ConsoleColors.RESET);
        } else {
            throw new InvalidAmountException(ConsoleColors.RED+"\nInvalid deposit amount!\n"+ConsoleColors.RESET);
        }
    }
	 public void checkBalance(Account account) {
	        System.out.println(ConsoleColors.BLUE+"\nCurrent Balance: " + account.getBalance()+"\n"+ConsoleColors.RESET);
	    }
}
