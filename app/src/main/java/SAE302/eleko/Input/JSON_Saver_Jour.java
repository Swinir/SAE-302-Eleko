package SAE302.eleko.Input;


public class JSON_Saver_Jour {

    SAE302.eleko.Data.Jour[] arr_jours;

    public JSON_Saver_Jour(int nb_jours) {
        arr_jours = new SAE302.eleko.Data.Jour[nb_jours];
    }

    public void JSON_save_day(SAE302.eleko.Data.Jour jour, int index) {
        arr_jours[index] = jour;
    }

    public SAE302.eleko.Data.Jour[] getArr_jours() {
        return arr_jours;
    }
}
