package Naloga.Booking;

import Naloga.Booking.Database.*;
import Naloga.Booking.Flights.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		Database db = new Database();
		SpringApplication.run(BookingApplication.class, args);

		String filePath = new File("").getAbsolutePath();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath + "\\Booking\\src\\main\\java\\Naloga\\Booking\\primarno.txt"));

			String fieldVal;
			while ((fieldVal = br.readLine()) != null) {
				String[] fields = fieldVal.split("-");
				Field newField = new Field(fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4]));
				System.out.println(newField.toString());
				db.insert(newField);
			}
		}catch (IOException e){
			System.out.println(e);
		}

		System.out.println(db.setNumOfAvailable("SA001", 49));
	}

}
