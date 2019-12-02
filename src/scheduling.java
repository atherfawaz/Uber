public class scheduling extends Thread {
    public void run() {
        try {
            //pass scheduled time minus current time to sleep
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //check how to interrupt main passenger's normal functionality?
        //Trip.iobj.interrupt();
    }
}
