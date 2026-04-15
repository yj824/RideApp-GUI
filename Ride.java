import java.text.NumberFormat;

public class Ride {

    private String riderName;
    private Address pickup;
    private Address dropoff;
    private boolean express;
    private String passengerRange;
    private double price;
    private String driver;

    public Ride(String riderName, Address pickup, Address dropoff,
                boolean express, String passengerRange, double price) {
        this.riderName = riderName;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.express = express;
        this.passengerRange = passengerRange;
        this.price = price;
        this.driver = null;
    }

    public boolean isAssigned() {
        return driver != null;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getQueueSummary(NumberFormat currencyFormat) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ride for ")
          .append(riderName)
          .append(" from ")
          .append(pickup.getCity())
          .append(" to ")
          .append(dropoff.getCity())
          .append(" for ")
          .append(currencyFormat.format(price))
          .append(isAssigned() ? " claimed by " + driver : " requested");
        return sb.toString();
    }

    @Override
    public String toString() {
        String driverName = (driver == null) ? "None" : driver;
        return "Pickup: " + pickup +
                " | Dropoff: " + dropoff +
                " | Express: " + express +
                " | Num Passengers: " + passengerRange +
                " | Rider: " + riderName +
                " | Driver: " + driverName;
    }
}
