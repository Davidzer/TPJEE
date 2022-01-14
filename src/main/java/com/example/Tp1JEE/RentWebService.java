package com.example.Tp1JEE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentWebService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    RentRepository rent;
    VehiculeRepository vehic;
    PersonRepository per;

    @Autowired
    public RentWebService(RentRepository rent, VehiculeRepository vehic){
        this.rent = rent;
        this.vehic = vehic;
    }

    /**
     * http://localhost:8080/cars
     */
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Vehicule> listeOfCars(){


       return vehic.findAll();
    }

    @RequestMapping(value = "/cars/{plateNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Vehicule> Car(@PathVariable("plateNumber") String plateNumber){

        return vehic.findById(plateNumber);
    }

    @RequestMapping(value = "/cars/{plateNumber}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void DeleteCar(@PathVariable("plateNumber") String plateNumber) throws Exception{

        try {
            vehic.deleteById(plateNumber);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }

    @RequestMapping(value = "/cars/{plateNumber}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void AddOrDelRent(@PathVariable("plateNumber") String plateNumber) throws Exception{

        try {
            boolean b = true;

            Optional<Vehicule> v = vehic.findById(plateNumber);
            Vehicule v1 = (Vehicule) v.get();
            Iterable<Rent> Rlist= rent.findAll();

            for (Rent r: Rlist) {
                if (r.getVehicule() == v1){
                    rent.delete(r);
                    b = false;
                }
            }
            if (b){
                Person p = new Person("Coco", 25);

                Rent r = new Rent();
                r.setVehicule(v1);
                r.setDebutDate(simpleDateFormat.parse("22-02-2222"));
                r.setFinDate(simpleDateFormat.parse("22-02-3333"));
                r.setPerson(p);

                rent.save(r);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }



    @GetMapping("/voitures/{prix}")
    public Car getVoiture(@PathVariable(value = "prix") int price) {
        System.out.println(price);
        return new Car("DD55FF", "Fiat", price);
    }

    /**
     * utiliser post man ou programmer
     * @param car
     */

    @DeleteMapping("/voitures")
    public void delete(@RequestBody Car car) {
        System.out.println(car);
    }

}
