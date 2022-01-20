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

 /**   @RequestMapping(value = "/cars/{plateNumber}", method = RequestMethod.PUT)
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


    }**/

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

            Rent re = new Rent();
            if (rentB && newRent != null) {



                for (Rent r : rent.findAll()) {


                    if (r.getVehicule().getPlateNumber().equals(plateNumber)) {
                        System.out.println("la voiture est déja louer");
                        break;
                    } else if (newRent.getDebut() == null || newRent.getFin() == null || newRent.getPerson() == null) {
                        System.out.println("Merci de renseigner les dates de la location et la personne qui réalise la location");
                        break;
                    } else {

                        Optional<Vehicule> o = vehic.findById(plateNumber);
                        Vehicule car = (Vehicule) o.get();

                        try {
                            re.setDebutDate(simpleDateFormat.parse(newRent.getDebut()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            re.setFinDate(simpleDateFormat.parse(newRent.getFin()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        re.setPerson(newRent.getPerson());
                        re.setVehicule(car);
                        rent.save(re);
                        System.out.println("la voiture "+ plateNumber +" est maintenant loué du " + re.getDebutDate() + " au " + re.getFinDate() + " par " + re.getPerson());
                        break;
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
                if (suprent != null){
                    rent.delete(suprent);
                    System.out.println("la voiture " + plateNumber + " n'est plus en location");
                }
                else {
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
