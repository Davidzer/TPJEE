package com.example.Tp1JEE;

import javax.persistence.*;


@Entity
public class Van extends Vehicule {

    private int maxWeight;

    public Van(String Plate, String marque, int maxWeight)
    {
        super(Plate, marque);
        this.maxWeight = maxWeight;
    }

    public Van() {
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
