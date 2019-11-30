
import java.util.*;

public class Vehicle
{
    private String registrationNum;
    private String condition;
    private String make;
    private Integer mode;
    private String manufacturer;

    Vehicle(String registrationNum, String condition, String make, Integer mode, String manufacturer)
    {
        this.registrationNum = registrationNum;
        this.condition = condition;
        this.make = make;
        this.mode = mode;
        this.manufacturer = manufacturer;
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