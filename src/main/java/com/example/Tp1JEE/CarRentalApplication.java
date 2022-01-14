package com.example.Tp1JEE;

import jdk.nashorn.internal.parser.DateParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RentRepository repository){


	return args -> {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

		Person tintin = new Person();
		tintin.setAge(20);
		tintin.setNom("Tintin");
		Person popol = new Person();
		popol.setAge(50);
		popol.setNom("popol");

		Car car = new Car();
		car.setPlateNumber("11AA22");
		car.setMarque("Ferrari");
		car.setNumberOfSeats(5);

		Van van = new Van();
		van.setPlateNumber("55EE88");
		van.setMarque("Volkswagen");
		van.setMaxWeight(500);


		Date date1 = simpleDateFormat.parse("21-05-2021");
		Date date2 = simpleDateFormat.parse("08-08-2022");

		Date date3 = simpleDateFormat.parse("11-09-2021");
		Date date4 = simpleDateFormat.parse("10-12-2022");

		Rent rent = new Rent();
		rent.setDebutDate(date1);
		rent.setFinDate(date2);
		rent.setVehicule(car);
		rent.setPerson(tintin);

		Rent rent1 = new Rent();
		rent1.setDebutDate(date3);
		rent1.setFinDate(date4);
		rent1.setVehicule(van);
		rent1.setPerson(popol);

		car.getRent().add(rent);
		tintin.getRent().add(rent);

		van.getRent().add(rent1);
		popol.getRent().add(rent1);

		repository.save(rent);
		repository.save(rent1);
	};

	}
}


