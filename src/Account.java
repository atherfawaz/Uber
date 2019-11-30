
import java.util.*;

public class Account
{
    private String accountNum;
    private double totalCredit;

    public Account(String accountNum, double totalCredit) {
        this.accountNum = accountNum;
        this.totalCredit = totalCredit;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNUm(String accountNUm) {
        this.accountNum = accountNum;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }
    public Boolean addCredit(Double amount)
    {
        totalCredit += amount;
        System.out.println("RS" + amount + " has been added to your account. The total amount in your account is now " + totalCredit);
        return true;
    }
    public Boolean debitAccount(Double amount)
    {
        if (amount>totalCredit)
        {
            System.out.println("Sorry, you do not have enough credit to pay " + amount + " through your account.");
            return false;
        }
        totalCredit -= amount;
        return true;
    }
    public Boolean verifyDetails(List<String> details)
    {
        //TODO implement here
        return null;
    }
    public void cashout (Double amount)
    {
        //TODO implement here
    }
}