
import java.util.*;

public class Vehicle
{
    private String registrationNum;
    private String condition;
    private String make;
    private Integer mode;
    private String manufacturer;

    Vehicle(String a, String b, String c, Integer d, String e)
    {
        registrationNum = a;
        condition = b;
        make = c;
        mode = d;
        manufacturer = e;
    }
    public String getRegistrationNum()
    {
        return this.registrationNum;
    }
    public String getCondition()
    {
        return this.condition;
    }

    public String getMake() {
        return make;
    }

    public Integer getMode() {
        return mode;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}