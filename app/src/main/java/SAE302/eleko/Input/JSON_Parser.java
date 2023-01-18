package SAE302.eleko.Input;

import SAE302.eleko.Data.Jour;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import java.text.ParseException;

public class JSON_Parser {

    JSON_Saver_Jour json_saver_jour;
    //This class is used to parse the JSON data

    public Jour[] onCreate(String data, Activity activity) throws JSONException {

        String jsonString = data ;
        JSONArray jsonArray = new JSONArray(jsonString); //Create a JSON array from the string
        json_saver_jour = new JSON_Saver_Jour(jsonArray.length()); //Create a JSON_Saver_Jour object to save the data
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = new JSONObject(jsonArray.get(i).toString()); //Create a JSON object from the JSON array
            JSONArray arr_signaux = obj.getJSONArray("signals"); //Create a JSON array from the JSON object
            for (int j = 0; j < arr_signaux.length(); j++)
            {
                String GenerationFichier = arr_signaux.getJSONObject(j).getString("GenerationFichier"); //All the data are saved in a string
                String jour = arr_signaux.getJSONObject(j).getString("jour");
                Integer dvalue = arr_signaux.getJSONObject(j).getInt("dvalue");
                String message = arr_signaux.getJSONObject(j).getString("message");
                JSONArray values = arr_signaux.getJSONObject(j).getJSONArray("values");
                JSON_Saver_Heure json_saverHeure = new JSON_Saver_Heure();


                for (int k = 0; k < values.length(); k++) //For each hours
                {
                    Integer pas = values.getJSONObject(k).getInt("pas");
                    Integer hvalue = values.getJSONObject(k).getInt("hvalue");


                    json_saverHeure.JSON_Saver_Hours(pas, hvalue); //Save the data in the JSON_Saver_Heure object
                }
                try {
                    json_saverHeure.JSON_Saver_Day(GenerationFichier, jour, dvalue, message, json_saverHeure.getArr_24h()); //Save the data in the JSON_Saver_Jour object
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                json_saver_jour.JSON_save_day(json_saverHeure.getJour(), i); //Save the data in the JSON_Saver_Jour object
            }
        }
        return json_saver_jour.getArr_jours();
    }
}
