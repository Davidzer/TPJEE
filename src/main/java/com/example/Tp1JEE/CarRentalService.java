package com.example.Tp1JEE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CarRentalService {
	
	private List<Car> cars = new ArrayList<Car>();


	public CarRentalService() {

	}

	@GetMapping("/cars")
	public List<Car> getListOfCars(){
		return cars;
	}
	
	@PostMapping("/cars")
	public void addCar(@RequestBody Car car) throws Exception{
		System.out.println(car);
		cars.add(car);
	}

	@GetMapping("/cars/{plateNumber}")
	public Car getCar(@PathVariable(value = "plateNumber") String plateNumber){
		for(Car car: cars){
			if(car.getPlateNumber().equals(plateNumber)){
				return car;
			}
			else{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plaque introuvable");
			}
		}
		return null;
	}

	/**@PutMapping("/cars/{plateNumber}")
	@ResponseStatus(HttpStatus.OK)
	public void rentCar(@PathVariable("plateNumber") String plateNumber, @RequestParam(value = "rent", required = true) boolean rent, @RequestBody(required = false) Dates dates){

			for (Vehicule vehicule : cars) {

				if (vehicule.getPlateNumber().equals(plateNumber)) {
					if (vehicule.getRent() == true && rent == true)
					{
						System.out.println("la voiture est déja louer");
					}
					else if (rent == true){
						car.setRent(rent);

						if (rent == true) {
							if (dates == null) {
								System.out.println("Merci de renseigner la date de la location");
							} else {
								car.setDateloc(dates);
								System.out.println(car.getDateloc());
								System.out.println(car.getPlateNumber());
								System.out.println("la voiture est loué");
							}

						}

					}
					if (rent == false && car.getRent() == false)
					{
						System.out.println("La voiture est déja libre");
					}
					else if (rent == false) {
						car.setRent(rent);
						dates = null;
						car.setDateloc(dates);
						System.out.println("la voiture " + car.getPlateNumber() + " est revenu de location");
					}



				}

				if (plateNumber == null)
				{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plaque introuvable");
				}
			}
	} **/

}
	

