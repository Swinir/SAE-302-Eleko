package Data;

public class Heure{



    //Definition des heures
    private Etat heure;


    //Constructeur
    public Heure(int heure, Integer hvalue) {
        this.heure = new Etat(heure, hvalue);
    }

    public Etat getHeure() {
        return heure;
    }
    public Integer getHvalue() {
        return heure.getHvalue();
    }

}
