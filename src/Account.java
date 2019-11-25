
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
    public Boolean credit(Double amount)
    {
        //TODO implement here
        return null;
    }
    public Boolean debitAccount(Double amount)
    {
        //TODO implement here
        return null;
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