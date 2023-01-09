package SAE302.eleko;

import androidx.fragment.app.Fragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import SAE302.eleko.Data.Jour;
import SAE302.eleko.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Jour[] data = (Jour[]) getIntent().getSerializableExtra("data"); //Get data downloaded and parsed from loadingscreen activity

        replaceFragment(new HomeFragment(data));

        if (data != null) {
            Toast.makeText(this, "Data received", Toast.LENGTH_SHORT).show(); //TODO: Remove after testing
        }
        else {
            Toast.makeText(this, "No data received, app will not function correctly", Toast.LENGTH_LONG).show();
        }

        Date currentTime = Calendar.getInstance().getTime(); //Get current day and time




        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.day1:
                    //Put the data before committing the data
                    replaceFragment(new HomeFragment(data));
                    break;
                case R.id.day2:
                    replaceFragment(new Day2Fragment(data));
                    break;
                case R.id.day3:
                    replaceFragment(new Day3Fragment(data));
                    break;
                case R.id.day4:
                    replaceFragment(new Day4Fragment(data));
                    break;
            }
            return true;
        });

        //Listen for when the user clicks the settings button
        binding.TopNavigationView.setOnNavigationItemSelectedListener(v -> {
            if (v.getItemId() == R.id.settingbutton) {
                Intent intent_settings = new Intent(this, Settings.class);
                startActivity(intent_settings);
            }
            return true;
        });


        //Remove the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}