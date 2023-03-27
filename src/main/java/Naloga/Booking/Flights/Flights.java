package Naloga.Booking.Flights;

public class Flights {
    String id;
    String origin;

    public Flights(String id, String origin) {
        this.id = id;
        this.origin = origin;
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

    @Override
    public String toString() {
        return "Flights{" +
                "id='" + id + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
