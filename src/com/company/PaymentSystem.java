package com.company;

public class PaymentSystem {


  public Boolean verifyFromBank(String IBAN) {
    if(IBAN.length() == 14)
    {
      return true;
    }
    return false;
  }

  public Boolean withdrawToBank(Double amount, String IBAN) {
    //withdrawing broooo ayeeee
    //Bank withdrawal are out of our scope, there exists no bank in this virtual world
    if(verifyFromBank(IBAN))
    {
      return true;
    }
    return false;
  }

  public Boolean makePayment(Double amount, Driver driverAccount) {
    driverAccount.addToEarnings(amount);
    return true;
  }
}
