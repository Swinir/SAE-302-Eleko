package SAE302.eleko.Input;

import java.text.ParseException;

public class JSON_Saver_Heure {
    //This class is used to save the parsed JSON data
    SAE302.eleko.Data.Heure[] arr_24h;
    SAE302.eleko.Data.Jour current_day;
    public JSON_Saver_Heure() {
        arr_24h = new SAE302.eleko.Data.Heure[24];
    }

    public void JSON_Saver_Day(String GenerationFichier, String jour, Integer dvalue, String message,SAE302.eleko.Data.Heure[] arr_24h) throws ParseException {
        //This method is used to save the parsed JSON data for the day
        current_day = new SAE302.eleko.Data.Jour(GenerationFichier, jour, dvalue, message, arr_24h); //Create a Jour object
    }
    public void JSON_Saver_Hours(Integer pas, Integer hvalue) {
        arr_24h[pas] = new SAE302.eleko.Data.Heure(pas, hvalue); //Create a Heure object
    }



    public SAE302.eleko.Data.Heure[] getArr_24h() {
        return arr_24h;
    }

    public SAE302.eleko.Data.Jour getJour() {
        return current_day;
    }
}
