package com.example.Tp1JEE;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Person {

    List<Rent> rent = new ArrayList<Rent>();
    String nom;
    int age;

    public Person() {}

    public Person(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public void setRent(List<Rent> rrent) {
        this.rent = rrent;
    }

    @OneToMany(mappedBy = "person")
    public List<Rent> getRent() {
        return rent;
    }

    @Id
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}