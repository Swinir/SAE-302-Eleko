package SAE302.eleko;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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