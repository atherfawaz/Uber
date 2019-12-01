public class Riding extends Thread {

  public void run(Riding obj) {
    System.out.println("Riding for 5 seconds...");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Ending ride...");
  }

  public void startWrapper(Input obj) {
    this.start();
    obj.interrupt();
  }
}
