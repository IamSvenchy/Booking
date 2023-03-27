package Naloga.Booking.Database;

public class Field {
    String id;
    String origin;
    String destination;
    String flightLineName;
    int NumberOfAvailableSeats;

    public Field(String id, String origin, String destination, String flightLineName, int numberOfAvailableSeats) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.flightLineName = flightLineName;
        NumberOfAvailableSeats = numberOfAvailableSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightLineName() {
        return flightLineName;
    }

    public void setFlightLineName(String flightLineName) {
        this.flightLineName = flightLineName;
    }

    public int getNumberOfAvailableSeats() {
        return NumberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
        NumberOfAvailableSeats = numberOfAvailableSeats;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", flightLineName='" + flightLineName + '\'' +
                ", NumberOfAvailableSeats=" + NumberOfAvailableSeats +
                '}';
    }

    public String serialize(){
        return this.id + "," + this.origin + "," + this.destination + "," + this.flightLineName + "," + this.NumberOfAvailableSeats + '\n';
    }
}
