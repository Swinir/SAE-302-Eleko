package Input;

import Data.Jour;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;

public class JSON_Parser {

    JSON_Saver_Jour json_saver_jour;
    //This class is used to parse the JSON data
    public JSON_Parser(String data) {
        String jsonString = data ; //This is the JSON data
        JSONArray jsonArray = new JSONArray(jsonString); //Converts the string into a JSON array
        json_saver_jour = new JSON_Saver_Jour(jsonArray.length()); //This is the number of days
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = new JSONObject(jsonArray.get(i).toString()); //Converts the JSON array into a JSON object
            JSONArray arr_signaux = obj.getJSONArray("signals"); //Gets the array of signals
            for (int j = 0; j < arr_signaux.length(); j++)
            {
                String GenerationFichier = arr_signaux.getJSONObject(j).getString("GenerationFichier"); //All the getters
                String jour = arr_signaux.getJSONObject(j).getString("jour");
                Integer dvalue = arr_signaux.getJSONObject(j).getInt("dvalue");
                String message = arr_signaux.getJSONObject(j).getString("message");
                JSONArray values = arr_signaux.getJSONObject(j).getJSONArray("values");
                JSON_Saver_Heure json_saverHeure = new JSON_Saver_Heure(); //This is the class that saves the hours


                for (int k = 0; k < values.length(); k++) //This is the loop that goes through the hours
                {
                    Integer pas = values.getJSONObject(k).getInt("pas");
                    Integer hvalue = values.getJSONObject(k).getInt("hvalue");


                    json_saverHeure.JSON_Saver_Hours(pas, hvalue); //This is the method that saves the hours into the hours array
                }
                try {
                    json_saverHeure.JSON_Saver_Day(GenerationFichier, jour, dvalue, message, json_saverHeure.getArr_24h()); //This is the method that saves the day into the days array
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                json_saver_jour.JSON_save_day(json_saverHeure.getJour(), i); //This is the method that saves the day into the days array
            }
        }
    }

    public Jour[] return_data() {
        return json_saver_jour.getArr_jours();
    }
}
