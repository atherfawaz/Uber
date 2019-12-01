import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ReadJSON {

  public static void read() {
    String path = "D:\\College\\Fall 2019\\Object Oriented Analysis and Design\\Project\\Uber\\src\\Drivers.json";
    try {
      String contents = new String((Files.readAllBytes(Paths.get(path))));
      JSONObject o = new JSONObject(contents);
      String temp = o.getString("name");
      System.out.println(temp);
    } catch (IOException | JSONException e) {
      e.printStackTrace();
    }
  }
}
