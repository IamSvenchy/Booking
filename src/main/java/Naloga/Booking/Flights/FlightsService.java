package Naloga.Booking.Flights;

import Naloga.Booking.Database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService {

    private Database db = new Database();
    public List<Field> getAll(){
        return db.getAll();
    }

    public Field get(String id){
        return db.get(id);
    }

    public void bookSeat(String id, int numberOfSeats){
        Field cur = db.get(id);
        cur.setNumberOfAvailableSeats(cur.getNumberOfAvailableSeats() - numberOfSeats);
        db.insert(cur);
    }


}
