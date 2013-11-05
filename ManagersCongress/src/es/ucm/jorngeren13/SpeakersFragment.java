package es.ucm.jorngeren13;

import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SpeakersFragment extends JG13Fragment {
    
    /** List containing the speakers **/
    private ListView list;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        final View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
        list = (ListView) rootView.findViewById(R.id.list);
        
        String[] cursorFields = new String[] { "name", "position" };
        int[] viewFields = new int[] { R.id.speaker_name, R.id.speaker_position };
        
        list.setAdapter(new SimpleCursorAdapter(rootView.getContext(), R.layout.speakers_list_row, JG13Data.getSpeakersCursor(), cursorFields, viewFields, 0));
        
        return rootView;
    }

}
