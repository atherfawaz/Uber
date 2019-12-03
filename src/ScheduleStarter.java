import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduleStarter implements Runnable {
    private final Waiting obj;
    private Trip trip;

    ScheduleStarter(Waiting passedobj, Trip passedtrip) {
        this.obj = passedobj;
        this.trip = passedtrip;
    }

    @Override
    public void run() {
        try {
            startRideAndStuff();
        } catch (InterruptedException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void startRideAndStuff () throws InterruptedException, ParseException {
        synchronized (obj) {
            obj.wait();

            //
            trip.setDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            trip.getPassenger().addRide(trip);
            trip.startRide();
            trip.getPassenger().setStatus(true);
            trip.getDriver().setStatus(true);
            if (Trip.tripchoice != 5) {
                trip.getPassenger().initiatePayment("notcash", trip.getPassenger().getCurrentRide().getTotalCost(),
                        trip.getDriver().getCurrentRide().getDriver());
                System.out.println(
                        "Now that the trip is complete, what rating would you like to give the driver, "
                                + trip.getDriver().getName()
                                + ", for this trip?\nRate on a scale of 1 (very unsatisfactory) to 5 (highly satisfactory).");
                Uber.mySleep(2000);
                double rating_ = Uber.myRand(1, 5);
                System.out.println("You have given the driver a rating of 3.");
                trip.getPassenger().setStatus(false);
                trip.getDriver().setStatus(false);
            } else {
                System.out.println("Trip ended prematurely");
                trip.getPassenger().setStatus(false);
                trip.getDriver().setStatus(false);
            }
            //
        }
    }
}
