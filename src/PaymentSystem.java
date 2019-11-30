import java.util.List;

public class PaymentSystem {

  private static PaymentSystem obj;

  private PaymentSystem() {
    //
  }

  public static PaymentSystem getInstance() {
    if (obj == null) {
      obj = new PaymentSystem();
      return obj;
    } else {
      return obj;
    }
  }


  public Boolean verifyFromBank(List<String> details) {
    //TODO implement here;
    return null;
  }

  public Boolean withdrawToBank(Double amount, String IBAN) {
    //TODO implement here
    return null;
  }

  public Boolean makePayment(Double amount, String driverAccount) {
    //TODO implement here
    return null;
  }
}
