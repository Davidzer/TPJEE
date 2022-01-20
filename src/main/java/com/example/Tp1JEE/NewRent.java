package com.example.Tp1JEE;

public class NewRent {

    private String debut;
    private String fin;
    private Person person;

    public NewRent() {
    }

    public NewRent(String debut, String fin, Person person) {
        this.debut = debut;
        this.fin = fin;
        this.person = person;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
