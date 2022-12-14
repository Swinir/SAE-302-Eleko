package Data;

import java.util.Date;

public class Jour {
    //Stocke la date et +

    String GenerationFichier;
    String jour; //Might change this to date format
    Integer dvalue;
    String message;
    Data.Heure[] arr_24h;

    public Jour(String GenerationFichier, String jour, Integer dvalue, String message, Data.Heure[] arr_24h) {
        this.GenerationFichier = GenerationFichier;
        this.jour = jour;
        this.dvalue = dvalue;
        this.message = message;
        this.arr_24h = arr_24h;
    }

}
