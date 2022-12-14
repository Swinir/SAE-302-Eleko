package SAE302.eleko;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONException;

import SAE302.eleko.Input.JSON_Parser;
import SAE302.eleko.Input.Web;

public class LoadingScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        //Remove the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        String inputURL = "http://isis.unice.fr/~mgautero/ext/sae302/index.php?action=get"; //Dummy URL for testing

        String Web_Data = new Web().onCreate(inputURL,LoadingScreen.this); //Get the JSON data from the web

        JSON_Parser json_parser = null;
        try {
            SAE302.eleko.Data.Jour[] data_array = new JSON_Parser().onCreate(Web_Data, LoadingScreen.this); //Parse the JSON data
            System.out.println(data_array[0]);
        } catch (JSONException e) {
            Toast.makeText(this,"Erreur de parsing des données json", Toast.LENGTH_LONG).show();
        }



        //Start the main activity after 3 seconds
        //This is temporary, we will need to change this trigger to the loading screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);



    }
}