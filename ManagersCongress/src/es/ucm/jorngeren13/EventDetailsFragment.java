package es.ucm.jorngeren13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EventDetailsFragment extends JG13Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        final View rootView = inflater.inflate(R.layout.fragment_event_details, container, false);
        return rootView;
    }

}
