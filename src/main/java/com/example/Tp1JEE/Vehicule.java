package com.example.Tp1JEE;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicule {

    private String plateNumber;
    private String Marque;
    private List<Rent> rent = new ArrayList<Rent>();


    public Vehicule() {
    }

    public Vehicule(String Plaque, String marque)
    {
        this.plateNumber = Plaque;
        this.Marque = marque;
    }

    @Id
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    @OneToMany(mappedBy = "vehicule")
    public List<Rent> getRent() {
        return rent;
    }

    public void setRent(List<Rent> rrent) {
        this.rent = rrent;
    }


    public String FoundByPlate(String plate){
        if (plate == this.plateNumber)
        {
            return this.plateNumber;
        }
        else {
            return null;
        }
    }

}
