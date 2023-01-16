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

        Jour[] data = (Jour[]) getIntent().getSerializableExtra("data"); //Get data downloaded and parsed from loadingscreen activity

        replaceFragment(new HomeFragment(data));

        if (data == null) {
            Toast.makeText(this, "No data received, app will not function correctly", Toast.LENGTH_LONG).show();
        }


        //We need to change the menu bar to show the current day
        //Getting today's date and putting it in java's date format
        Date todays_date = new Date();
        todays_date = getZeroTimeDate(todays_date);
        Date tomorrows_date = new Date(todays_date.getTime() + (1000 * 60 * 60 * 24));
        Date todayplustwo_date = new Date(todays_date.getTime() + (1000 * 60 * 60 * 24) * 2);
        Date todayplusthree_date = new Date(todays_date.getTime() + (1000 * 60 * 60 * 24) * 3);

        //Setting the menu bar to show the current day

        for (Jour jour : data) {
            //Checking if date is same day as today
            if (jour.getDate().compareTo(todays_date) == 0) {
                //If it is, we change the menu bar to show the current day
                //But first, we get the date of the day we want to display
                Date date = jour.getDate();
                String dayName = getDayName(date);
                binding.bottomNavigationView.getMenu().getItem(0).setTitle(dayName);
            } else if (jour.getDate().compareTo(tomorrows_date) == 0) {
                Date date = jour.getDate();
                String dayName = getDayName(date);
                binding.bottomNavigationView.getMenu().getItem(1).setTitle(dayName);
            } else if (jour.getDate().compareTo(todayplustwo_date) == 0) {
                Date date = jour.getDate();
                //We get the day of the week
                String dayName = getDayName(date);
                binding.bottomNavigationView.getMenu().getItem(2).setTitle(dayName);
            } else if (jour.getDate().compareTo(todayplusthree_date) == 0) {
                Date date = jour.getDate();
                //We get the day of the week
                String dayName = getDayName(date);
                binding.bottomNavigationView.getMenu().getItem(3).setTitle(dayName);
            }
        }

        //Find the current day and set the corresponding data to be displayed


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
                intent_settings.putExtra("data", data);
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

    private Date getZeroTimeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        return date;
    }

    private String getDayName(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        //We find the name of the day of the week
        String dayName = "";
        switch (day) {
            case 1:
                dayName = ("Dimanche");
                break;
            case 2:
                dayName = ("Lundi");
                break;
            case 3:
                dayName = ("Mardi");
                break;
            case 4:
                dayName = ("Mercredi");
                break;
            case 5:
                dayName = ("Jeudi");
                break;
            case 6:
                dayName = ("Vendredi");
                break;
            case 7:
                dayName = ("Samedi");
                break;
        }
        return dayName;
    }
}