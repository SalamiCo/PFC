package es.ucm.jorngeren13;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class EventDetailsFragment extends JG13Fragment {
    
    Button details_info_1, details_info_2;
    String lat_1 = "40.424979";
    String lng_1 = "-3.707513";
    String lat_2 = "40.440";
    String lng_2 = "-3.729";
    
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_event_details, container, false);
        
//        details_info_1 = (Button) rootView.findViewById(R.id.details_button_info_1);
//        details_info_2 = (Button) rootView.findViewById(R.id.details_button_info_2);
        details_info_1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick (View v) {
                getMap(lat_1, lng_1, "Paraninfo de la UCM");
            }
        });
        
        details_info_2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick (View v) {
                getMap(lat_2, lng_2, "Museo del Traje");
            }
        });
        return rootView;
    }
    
    private void getMap(String lat, String lng, String label){
        Intent intent = new Intent(Intent.ACTION_VIEW, 
                        Uri.parse("geo:" +lat +"," +lng +"?q=" +lat +"," +lng +"(" +label +")"));
        startActivity(intent);
    }

}
