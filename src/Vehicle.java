
import java.util.*;

public class Vehicle
{
    private String registrationNum;
    private String condition;
    private String make;
    private Integer model;
    private String manufacturer;

    Vehicle() {
    }
    Vehicle(String registrationNum, String condition, String make, Integer model, String manufacturer)
    {
        this.registrationNum = registrationNum;
        this.condition = condition;
        this.make = make;
        this.model = model;
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

    public Integer getModel() {
        return model;
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

    public void setModel(Integer mode) {
        this.model = mode;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}