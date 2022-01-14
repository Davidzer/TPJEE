package com.example.Tp1JEE;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rent {

    private long id;
    private Date debutDate;
    private Date finDate;
    private Vehicule vehicule;
    private Person person;

    public Rent(){}

    public Rent(Date debutDate, Date finDate, Vehicule vehicule, Person person) {
        this.debutDate = debutDate;
        this.finDate = finDate;
        this.vehicule = vehicule;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDebutDate() {
        return debutDate;
    }

    public Date getFinDate() {
        return finDate;
    }

    public void setDebutDate(Date debutDate) {
        this.debutDate = debutDate;
    }


    public void setFinDate(Date finDate) {
        this.finDate = finDate;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Vehicule getVehicule() {
        return vehicule;
    }


    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
