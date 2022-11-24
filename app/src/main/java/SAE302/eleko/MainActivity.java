package SAE302.eleko;

import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import SAE302.eleko.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Remove the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.day1:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.day2:
                    Intent intent2 = new Intent(MainActivity.this, Day2Fragment.class);
                    startActivity(intent2);
                    break;
                case R.id.day3:
                    Intent intent3 = new Intent(MainActivity.this, Day3Fragment.class);
                    startActivity(intent3);
                    break;
                case R.id.day4:
                    Intent intent4 = new Intent(MainActivity.this, Day4Fragment.class);
                    startActivity(intent4);
                    break;
            }
            return true;
        });
    }
}