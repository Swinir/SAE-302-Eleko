package SAE302.eleko;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import SAE302.eleko.Data.Heure;
import SAE302.eleko.Data.Jour;

public class HomeFragment extends Fragment {

    private final Jour[] data;
    ImageView img_france;
    ImageView[] imageViewHoursArray;
    ViewGroup root;

    public HomeFragment(Jour[] data) {
        this.data = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Here we get all the different id's for the images we might need to change
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        img_france = root.findViewById(R.id.imageViewMap);
        imageViewHoursArray = new ImageView[24]; //We have 24 bars for each hour so we need 24 images
        imageViewHoursArray[0] = root.findViewById(R.id.imageViewLegend3);
        imageViewHoursArray[1] = root.findViewById(R.id.imageViewHours2);
        imageViewHoursArray[2] = root.findViewById(R.id.imageViewHours3);
        imageViewHoursArray[3] = root.findViewById(R.id.imageViewHours4);
        imageViewHoursArray[4] = root.findViewById(R.id.imageViewHours5);
        imageViewHoursArray[5] = root.findViewById(R.id.imageViewHours6);
        imageViewHoursArray[6] = root.findViewById(R.id.imageViewHours7);
        imageViewHoursArray[7] = root.findViewById(R.id.imageViewHours8);
        imageViewHoursArray[8] = root.findViewById(R.id.imageViewHours9);
        imageViewHoursArray[9] = root.findViewById(R.id.imageViewHours10);
        imageViewHoursArray[10] = root.findViewById(R.id.imageViewHours11);
        imageViewHoursArray[11] = root.findViewById(R.id.imageViewHours12);
        imageViewHoursArray[12] = root.findViewById(R.id.imageViewHours13);
        imageViewHoursArray[13] = root.findViewById(R.id.imageViewHours14);
        imageViewHoursArray[14] = root.findViewById(R.id.imageViewHours15);
        imageViewHoursArray[15] = root.findViewById(R.id.imageViewHours16);
        imageViewHoursArray[16] = root.findViewById(R.id.imageViewHours17);
        imageViewHoursArray[17] = root.findViewById(R.id.imageViewHours18);
        imageViewHoursArray[18] = root.findViewById(R.id.imageViewHours19);
        imageViewHoursArray[19] = root.findViewById(R.id.imageViewHours20);
        imageViewHoursArray[20] = root.findViewById(R.id.imageViewHours21);
        imageViewHoursArray[21] = root.findViewById(R.id.imageViewHours22);
        imageViewHoursArray[22] = root.findViewById(R.id.imageViewHours23);
        imageViewHoursArray[23] = root.findViewById(R.id.imageViewHours24);

        TextView PrevisionMessage = root.findViewById(R.id.textViewPrevisionMessage);

        //Getting today's date and putting it in java's date format
        Date todays_date = new Date(); //Getting today's date
        todays_date = getZeroTimeDate(todays_date); //Setting the time to 00:00:00 to be able to compare it to the date in the data

        for (Jour jour : data) {
            //Checking if date is same day as today
            if (jour.getDate().compareTo(todays_date) == 0) {
                //If it is, then we can display the data
                //We start with the picture of France :
                switch(jour.getDvalue()){
                    case 0:
                        //Display gray picture of France
                        img_france.setImageResource(R.drawable.france_gray);
                        break;
                    case 1:
                        //Display green picture of France
                        img_france.setImageResource(R.drawable.france_green);
                        break;
                    case 2:
                        //Display orange picture of France
                        img_france.setImageResource(R.drawable.france_orange);
                        break;
                    case 3:
                        //Display red picture of France
                        img_france.setImageResource(R.drawable.france_red);
                        break;
                }

                //Then we display the message
                PrevisionMessage.setText(jour.getMessage());

                //To take care of the hours array, we need to do the following
                Heure[] Arr24h = jour.getArr_24h();
                int i = 0;
                for (Heure heure : Arr24h) {
                    switch(heure.getHvalue()){
                        case 0:
                            //Display gray bar so no need to change anything
                            break;
                        case 1:
                            //Display green bar
                            imageViewHoursArray[i].setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
                            break;
                        case 2:
                            //Display orange bar
                            imageViewHoursArray[i].setColorFilter(0xFFFFA500, PorterDuff.Mode.MULTIPLY);
                            break;
                        case 3:
                            //Display red bar
                            imageViewHoursArray[i].setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                            break;
                    }
                    i++;
                }
            }
        }
        // Inflate the layout for this fragment
        return root;
    }


    private Date getZeroTimeDate(Date date) { //This function is used to get the date at 00:00:00 so we can compare the dates in the data
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //Setting the calendar to the date we want to change
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        return date;
    }
}