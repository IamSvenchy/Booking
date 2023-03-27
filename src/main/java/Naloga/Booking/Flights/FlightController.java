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
    private FlightsService flightsService;



    @GetMapping("/flights")
    public List<Field> getAll(){
        return flightsService.getAll();
    }

    @PutMapping
    public void bookSeat(String flightID, int numberOfSeats){
        flightsService.bookSeat(flightID, numberOfSeats);
    }




}
