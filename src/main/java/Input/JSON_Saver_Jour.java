package Input;

public class JSON_Saver_Jour {

    Data.Jour[] arr_jours;

    public JSON_Saver_Jour(int nb_jours) {
        arr_jours = new Data.Jour[nb_jours];
    }

    public void JSON_save_day(Data.Jour jour, int index) {
        arr_jours[index] = jour;
    }

    public Data.Jour[] getArr_jours() {
        return arr_jours;
    }
}
