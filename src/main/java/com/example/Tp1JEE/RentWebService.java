package com.example.Tp1JEE;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RentWebService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    RentRepository rent;
    VehiculeRepository vehic;
    PersonRepository pers;

    @Autowired
    public RentWebService(RentRepository rent, VehiculeRepository vehic, PersonRepository pers){
        this.rent = rent;
        this.vehic = vehic;
        this.pers = pers;
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


    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<Rent> RentList() throws Exception{

        try {

            return rent.findAll();


        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    @RequestMapping(value = "/rent/{plateNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Rent> CarsRented(@PathVariable ("plateNumber") String plateNumber) throws Exception{

        try {
            List<Rent> rented = new ArrayList<Rent>();


            for (Rent r: rent.findAll()) {
                if (r.getVehicule().getPlateNumber().equals(plateNumber))
                {
                    rented.add(r);
                }


            }

            return  rented;

        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    @PutMapping("/rent/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentCar(@PathVariable("plateNumber") String plateNumber, @RequestParam(value = "rentB", required = true) boolean rentB, @RequestBody(required = false) NewRent newRent) throws Exception {


        try {

            boolean Exist = false;
            Rent re = new Rent();
            if (rentB && newRent != null) {
                for (Rent testExist : rent.findAll()) {
                    if (!vehic.existsById(plateNumber))
                    {
                        Exist = true;
                        System.out.println("Le véhicule n'éxiste pas");
                        break;
                    }
                    else if (testExist.getVehicule().getPlateNumber().equals(plateNumber) ) {
                        Exist = true;
                        System.out.println("Le véhicule est déja louer");
                        break;
                    }
                }


                if (!Exist) {
                    for (Rent r : rent.findAll()) {


                        if (newRent.getDebut() == null || newRent.getFin() == null || newRent.getNom() == null) {
                            System.out.println("Merci de renseigner les dates de la location et la personne qui réalise la location");
                            break;
                        } else {

                            Person p = new Person();
                            Optional<Vehicule> o = vehic.findById(plateNumber);
                            Vehicule car = (Vehicule) o.get();
                            re.setDebutDate(simpleDateFormat.parse(newRent.getDebut()));
                            re.setFinDate(simpleDateFormat.parse(newRent.getFin()));
                            if (pers.findById(newRent.getNom()).isPresent()) {
                                Optional<Person> op = pers.findById(newRent.getNom());
                                p = (Person) op.get();
                            } else {
                                p = new Person(newRent.getNom(), 0);
                            }
                            re.setPerson(p);
                            re.setVehicule(car);
                            rent.save(re);
                            System.out.println("la voiture " + plateNumber + " est maintenant loué du " + re.getDebutDate() + " au " + re.getFinDate() + " par " + re.getPerson());
                            break;
                        }

                    }

                }
            }
                if (!rentB) {

                    Rent suprent = new Rent();
                    suprent = null;
                    for (Rent r : rent.findAll()) {
                        if (r.getVehicule().getPlateNumber().equals(plateNumber)) {
                            suprent = r;

                        }

                    }
                    if (suprent != null) {
                        rent.delete(suprent);
                        System.out.println("la voiture " + plateNumber + " n'est plus en location");
                    } else {
                        System.out.println("Aucune voiture " + plateNumber + " est en location");
                    }
                }

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
    }

}
