package atmConsolesystem;

public class Admin {
	  public static final String Admin_userName = "admin";
	    public static final String Admin_password = "admin123";
	    
	   public boolean login(String user, String pass) {
		   return Admin_userName.equals(user) && Admin_password.equals(pass);
	   }
	   public void viewAccounts(Account[] accounts, int accountCount) {
		   if(accountCount==0) {
			   System.out.println(ConsoleColors.RED+"\nNo accounts Available\n"+ConsoleColors.RESET);
			   return;
		   }
			   System.out.println(ConsoleColors.BLUE+"\n--------List of Accounts-----\n"+ConsoleColors.RESET);
			   boolean found=false;
			   for(int i=0;i<accountCount;i++) {
				   if(accounts[i]!=null) {
					   found=true;
					   System.out.println("\nAccount: "+accounts[i].getAccountNum()+" | Name: "+accounts[i].getUserName()+"\n"); 
				   }
			   }
			   if(!found) {
				   System.out.println(ConsoleColors.RED+"\nNo accounts Available\n"+ConsoleColors.RESET);
			   }
		   }
	   
	   public int deleteAccount(Account[] accounts, int accountCount, String accNum) {
		   if(accountCount==0) {
			   System.out.println(ConsoleColors.YELLOW+"\nNo accounts to delete\n"+ConsoleColors.RESET);
			   return accountCount;
		   }
		   for(int i=0;i<accountCount;i++) {
			   if(accounts[i]!=null&&accounts[i].getAccountNum().equals(accNum)) {
				   accounts[i]=accounts[accountCount-1];
	                accounts[accountCount-1]=null;
	                accountCount--;
	                System.out.println(ConsoleColors.GREEN+"\nAccount deleted Successfully\n"+ConsoleColors.RESET);
	                return accountCount;
			   }
		   }
		   System.out.println(ConsoleColors.RED+"\nAccount not found\n"+ConsoleColors.RESET);
		   return accountCount;
	   }

}
