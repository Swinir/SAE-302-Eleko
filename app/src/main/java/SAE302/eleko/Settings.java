package SAE302.eleko;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import SAE302.eleko.Data.Jour;
import SAE302.eleko.databinding.ActivityMainBinding;
import SAE302.eleko.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {

    ActivitySettingsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Jour[] data = (Jour[]) getIntent().getSerializableExtra("data"); //Get data downloaded and parsed from loadingscreen activity

        Button buttoncontact = (Button) findViewById(R.id.buttoncontact);

        ImageView imageViewLegend1 = findViewById(R.id.imageViewLegend1);
        ImageView imageViewLegend2 = findViewById(R.id.imageViewLegend2);
        ImageView imageViewLegend3 = findViewById(R.id.imageViewLegend3);


        //Listen for when the user clicks the settings button
        binding.TopNavigationView.setOnNavigationItemSelectedListener(v -> {
            if (v.getItemId() == R.id.returnbutton) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
            return true;
        });

        //Listen for when the user clicks the contact button
        buttoncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://github.com/Swinir/SAE-302-Eleko/issues/new");
            }
        });


        //Set the color of the legend icons
        imageViewLegend1.setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        imageViewLegend2.setColorFilter(0xFFFFA500, PorterDuff.Mode.MULTIPLY);
        imageViewLegend3.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);


        //Remove the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }
    private void goToUrl (String url) { //This method is used to open a link in the browser of the phone
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}