package Input;

public class JSON_Saver_Heure {
    //This class is used to save the parsed JSON data
    Data.Heure[] arr_24h;
    Data.Jour current_day;
    public JSON_Saver_Heure() {
        arr_24h = new Data.Heure[24];
    }

    public void JSON_Saver_Day(String GenerationFichier, String jour, Integer dvalue, String message, Data.Heure[] arr_24h) {
        //This method is used to save the parsed JSON data for the day
        current_day = new Data.Jour(GenerationFichier, jour, dvalue, message, arr_24h);
    }
    public void JSON_Saver_Hours(Integer pas, Integer hvalue) {
        arr_24h[pas] = new Data.Heure(pas, hvalue);
    }



    public Data.Heure[] getArr_24h() {
        return arr_24h;
    }

    public Data.Jour getJour() {
        return current_day;
    }
}
