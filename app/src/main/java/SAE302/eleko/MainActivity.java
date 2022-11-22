package SAE302.eleko;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent_loading_screen = new Intent(this, LoadingScreen.class);
        startActivity(intent_loading_screen);
        setContentView(R.layout.activity_main);





    }
}