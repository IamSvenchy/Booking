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

    public boolean bookSeat(String id, int numberOfSeats){
        return db.setNumOfAvailable(id, numberOfSeats);


    }


}
