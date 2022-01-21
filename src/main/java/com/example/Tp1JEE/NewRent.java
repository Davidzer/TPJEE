package com.example.Tp1JEE;

public class NewRent {

    private String debut;
    private String fin;
    private String Nom;

    public NewRent() {
    }

    public NewRent(String debut, String fin, String nom) {
        this.debut = debut;
        this.fin = fin;
        this.Nom = nom;
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

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }
}
