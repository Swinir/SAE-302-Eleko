package SAE302.eleko.Data;

import java.io.Serializable;

public class Heure implements Serializable {
    //Definition des heures

    private Etat heure;

    //Constructeur
    public Heure(int heure, Integer hvalue) {
        this.heure = new Etat(heure, hvalue); //Create a object Etat for each hour
    }


    public Etat getHeure() {
        return heure;
    }
    public Integer getHvalue() {
        return heure.getHvalue();
    }
}