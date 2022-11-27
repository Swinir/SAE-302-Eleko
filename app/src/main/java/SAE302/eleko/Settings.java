package SAE302.eleko;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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



        //Listen for when the user clicks the settings button
        binding.TopNavigationView.setOnNavigationItemSelectedListener(v -> {
            if (v.getItemId() == R.id.returnbutton) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            return true;
        });



        //Remove the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}