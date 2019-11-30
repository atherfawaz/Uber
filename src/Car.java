
import java.util.*;

public class Car extends Vehicle
{
    private String type;
    Car() {
        //
    }
    Car(String a, String b, String c, Integer d, String e, String t)
    {
        super(a,b,c,d,e);
        this.type = t;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
}