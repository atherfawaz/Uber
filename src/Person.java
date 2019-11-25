
import java.util.*;

public class Person {
    private String name;
    private String nationalId;
    private Date dateOfBirth;
    private String email;
    private String phoneNum;
    private Boolean isDriver;

    public Person(String a, String b, Date c, String d, String e, Boolean f) {
        name = new String(e);
        nationalId = new String(b);
        dateOfBirth = c;
        email = new String(d);
        phoneNum = new String(e);
        isDriver = f;
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
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setDriver(Boolean driver) {
        isDriver = driver;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<String> getContactInfo()
    {
        List<String> rList = new ArrayList<String>();
        rList.add(this.email);
        rList.add(this.phoneNum);
        return rList; // Return email & phoneNum
    }
    public Trip getCurrentRide()
    {
        return null; //Implementation later
    }
    public void changeDetails()
    {
        //Implementation later
    }
}