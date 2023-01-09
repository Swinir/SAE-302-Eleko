package SAE302.eleko;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import SAE302.eleko.Data.Jour;


public class HomeFragment extends Fragment {

    private final Jour[] data;
    ImageView img_france;
    ViewGroup root;

    public HomeFragment(Jour[] data) {
        // Required empty public constructor
        this.data = data;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        img_france =(ImageView) root.findViewById(R.id.imageViewMap);


        //Getting today's date and putting it in java's date format
        Date todays_date = new Date();
        todays_date = getZeroTimeDate(todays_date);

        for (Jour jour : data) {
            //Checking if date is same day as today
            if (jour.getDate().compareTo(todays_date) == 0) {
                //If it is, then we can display the data
                //We start with the picture of France :
                switch(jour.getDvalue()){
                    case 1:
                        //Display green picture of France
                        img_france.setImageResource(R.drawable.france_green);
                    case 2:
                        //Display yellow picture of France
                        img_france.setImageResource(R.drawable.france_orange);
                    case 3:
                        //Display orange picture of France
                        img_france.setImageResource(R.drawable.france_red);
                }



            }
        }


        return root;
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
}