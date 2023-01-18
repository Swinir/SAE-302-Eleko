package SAE302.eleko.Data;

import java.io.Serializable;

public class Etat implements Serializable {
    //Définie les trois états possibles du réseau

    private int pas;
    private int hvalue;

    public Etat(int pas, int hvalue) {
        this.pas = pas; //Heure
        this.hvalue = hvalue; //Etat
    }

    public Integer getHvalue() {
        return hvalue;
    }
}
