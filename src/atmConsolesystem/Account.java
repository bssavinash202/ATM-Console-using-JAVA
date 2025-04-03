package atmConsolesystem;
import java.util.*;
public class Account {
	private String userName;
    private String location;
    private String accountNum;
    private String pin;
    private double balance;
    
    
	public Account(String userName, String location, String accountNum, String pin, double balance) {
		this.userName = userName;
		this.location = location;
		this.accountNum = accountNum;
		this.pin = pin;
		setBalance(balance);
	}


	public void setBalance(double balance) {
		if(balance>0) {
			this.balance = balance;
		}else
			this.balance=0;
		
	}


	public String getUserName() {
		return userName;
	}


	public String getLocation() {
		return location;
	}


	public String getAccountNum() {
		return accountNum;
	}


	public String getPin() {
		return pin;
	}


	public double getBalance() {
		return balance;
	}

	
    
    
}
