
import java.util.*;

public class Person {

  private String name;
  private String nationalId;
  private String dateOfBirth;
  private String email;
  private String phoneNum;
  private Boolean isDriver;
  protected Account account;

  public Person(String name, String nationalId, String dateOfBirth, String email, String phoneNum,
      Boolean isDriver, Account account) {

    this.name = new String(name);
    this.nationalId = new String(nationalId);
    this.dateOfBirth = new String(dateOfBirth);
    this.email = new String(email);
    this.phoneNum = new String(phoneNum);
    this.isDriver = isDriver;
    this.account = account;

  }

  public Person(Person passed) {
    this.account = passed.account;
    this.dateOfBirth = passed.dateOfBirth;
    this.email = passed.email;
    this.isDriver = passed.isDriver;
    this.name = passed.name;
    this.nationalId = passed.nationalId;
    this.phoneNum = passed.phoneNum;
  }

  public Person() { //used only by uberstaff
    name = "Default staff";
    nationalId = "35000-UBER-STAFF";
    dateOfBirth = new String("31-08-1998");
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

  public String getDateOfBirth() {
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

  public void setDateOfBirth(String dateOfBirth) {
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

  public static Person signUpAs(Person passed) {

    System.out.println("1. Sign up as a driver");
    System.out.println("2. Sign up as a passenger");
    Scanner sc = new Scanner(System.in);

    int choice = sc.nextInt();

    if (choice == 1) {
      Driver newDriver;
      newDriver = new Driver(passed);
      Boolean result = Automate.staff.get(0).verifyDriverDetails(newDriver);
      if (result) {
        return newDriver;
      } else {
        return null;
      }
    } else if (choice == 2) {
      return new Passenger(passed);
    } else {
      System.out.println("Invalid input.");
      return null;
    }
  }

  public static Person makePerson() {

    Person temp = new Person();
    Scanner sc = new Scanner(System.in);
    String choice;

    System.out.println("Enter your name: ");
    choice = sc.nextLine();
    temp.setName(choice);
    System.out.println("Enter your National Identification Number: ");
    choice = sc.nextLine();
    temp.setNationalId(choice);
    System.out.println("Enter your date of birth: ");
    choice = sc.nextLine();
    temp.setDateOfBirth(choice);
    System.out.println("Enter your email: ");
    choice = sc.nextLine();
    temp.setEmail(choice);
    System.out.println("Enter your phone number: ");
    choice = sc.nextLine();
    temp.setPhoneNum(choice);
    temp.setDriver(false);

    Account temp2 = new Account();
    System.out.println("Enter your account number: ");
    choice = sc.nextLine();
    temp2.setAccountNUm(choice);
    System.out.println("Enter your balance: ");
    Double val = sc.nextDouble();
    temp2.setTotalCredit(val);

    temp.setAccount(temp2);
    return temp;
  }
}