package SAE302.eleko;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json_Parser {

    public void decodeJSON (String s) {
        JSONObject jobj;
        JSONArray tobj;
        int vi;
        StringBuilder r;


        r = new StringBuilder("");

        try {
            jobj = new JSONObject(s);
            tobj = jobj.getJSONArray("questions"); //This needs to be changed as this part needs to be able to sort the data into the correct classes
            for (int i = 0; i < tobj.length(); i++) {
                vi = tobj.getInt(i);
                r.append(vi);
                r.append("-");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
