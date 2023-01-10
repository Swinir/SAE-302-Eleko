package Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Jour {
    //Stocke la date et +

    String GenerationFichier;
    String jour; //Might change this to date format
    Integer dvalue;
    String message;
    Data.Heure[] arr_24h;
    Date JourenDate;

    public Jour(String GenerationFichier, String jour, Integer dvalue, String message, Data.Heure[] arr_24h) throws ParseException {
        this.GenerationFichier = GenerationFichier;
        this.jour = jour;
        this.dvalue = dvalue;
        this.message = message;
        this.arr_24h = arr_24h;
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        JourenDate = originalFormat.parse(jour.substring(0,4)+ jour.substring(5,7) + jour.substring(8,10));
    }

    public Date getDate() {
        return JourenDate;
    }
    public String getGenerationFichier() {
        return GenerationFichier;
    }
    public String getJour() {
        return jour;
    }
    public Integer getDvalue() {
        return dvalue;
    }
    public String getMessage() {
        return message;
    }
    public Heure[] getArr_24h() {
        return arr_24h;
    }

}
