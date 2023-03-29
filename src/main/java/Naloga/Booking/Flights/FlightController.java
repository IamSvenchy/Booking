package Naloga.Booking.Flights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import Naloga.Booking.Database.*;

@RestController
public class FlightController {
    @Autowired
    private FlightsService flightsService = new FlightsService();



    @GetMapping("/flights")
    public List<Field> getAll(){
        return flightsService.getAll();
    }

    @PutMapping()
    public boolean bookSeat(String flightID, int numberOfSeats){
        return flightsService.bookSeat(flightID, numberOfSeats);
    }




}
