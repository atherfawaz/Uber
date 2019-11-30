
import java.util.*;

public class Person {

  private String name;
  private String nationalId;
  private Date dateOfBirth;
  private String email;
  private String phoneNum;
  private Boolean isDriver;
  protected Account account;

  public Person(String a, String b, Date c, String d, String e, Boolean f, Account acc) {
    name = new String(e);
    nationalId = new String(b);
    dateOfBirth = c;
    email = new String(d);
    phoneNum = new String(e);
    isDriver = f;
    account = acc;

  }

  public Person() {
    name = "Default staff";
    nationalId = "35000-UBER-STAFF";
    dateOfBirth = new Date();
    email = "support@uber.com";
    phoneNum = "111-111-111";
    isDriver = false;
  }

  public String getName() {
    return name;
  }

  public String getNationalId() {
    return nationalId;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public Boolean getIsDriver() {
    return isDriver;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDriver(Boolean driver) {
    isDriver = driver;
  }

  public void setAccount(Account acc) {
    account = acc;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public List<String> getContactInfo() {
    List<String> rList = new ArrayList<String>();
    rList.add(this.email);
    rList.add(this.phoneNum);
    return rList; // Return email & phoneNum
  }

  public Trip getCurrentRide() {
    return null; //Implementation later
  }

  public void changeDetails() {
    String choice = "";
    Scanner sc = new Scanner(System.in);
    while (choice.equalsIgnoreCase("5")) {
      System.out.println("1. Change Name");
      System.out.println("2. Change NID");
      System.out.println("3. Change Email");
      System.out.println("4. Change Phone Number");
      System.out.println("\n5. Cancel");

      System.out.println("\n Enter your choice: ");

      choice = sc.nextLine();
      String newVal = "";
      if (choice.equalsIgnoreCase("1")) {
        System.out.println("Enter the new name: ");
        newVal = sc.nextLine();
        this.setName(newVal);
      } else if (choice.equalsIgnoreCase("2")) {
        System.out.println("Enter the new NID");
        newVal = sc.nextLine();
        this.setNationalId(newVal);
      } else if (choice.equalsIgnoreCase("3")) {
        System.out.println("Enter the new Email");
        newVal = sc.nextLine();
        this.setEmail(newVal);
      } else if (choice.equalsIgnoreCase("4")) {
        System.out.println("Enter the new phone number");
        newVal = sc.nextLine();
        this.setPhoneNum(newVal);
      }
    }
  }
}