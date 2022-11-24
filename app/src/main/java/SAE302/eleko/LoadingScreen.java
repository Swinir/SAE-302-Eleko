package SAE302.eleko;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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


        String inputURL = "https://www.google.com"; //Dummy URL for testing

        String Web_Data = new Web().onCreate(inputURL,LoadingScreen.this); //Get the JSON data from the web

        new Json_Parser().decodeJSON(Web_Data); //Parse the JSON data and store it into the correct classes



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