package SAE302.eleko.Data;

import java.io.Serializable;
import java.util.Date;

public class Jour implements Serializable {
    //Stocke la date et +

    String GenerationFichier;
    String jour; //Might change this to date format
    Integer dvalue;
    String message;
    SAE302.eleko.Data.Heure[] arr_24h;

    public Jour(String GenerationFichier, String jour, Integer dvalue, String message, SAE302.eleko.Data.Heure[] arr_24h) {
        this.GenerationFichier = GenerationFichier;
        this.jour = jour;
        this.dvalue = dvalue;
        this.message = message;
        this.arr_24h = arr_24h;
    }

}
