import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Scheduling implements Runnable {

    private final Waiting obj;
    private String time;

    public Scheduling(Waiting passedobj, String time) {
        this.obj = passedobj;
        this.time = time;
    }

    @Override
    public void run() {
        countTime();
    }
//abc
    private void countTime() {
        synchronized (obj) {
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
            this.time = this.time.substring(11, 19); //Date passed in parameter
            // Now in hh:mm:ss format
            System.out.println("Here in scheduling");
            System.out.println(time);
            while (time.equals("15:33:00")) {
                //wake up the thread
                obj.notifyAll();
                time = "";
            }
        }
    }
}
