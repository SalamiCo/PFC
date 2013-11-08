package es.ucm.jorngeren13;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SpeakersFragment extends JG13Fragment {
    
    /** List containing the speakers **/
    private ListView list;
    private Cursor speakersCursor;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        final View rootView = inflater.inflate(R.layout.fragment_speakers, container, false);
        list = (ListView) rootView.findViewById(R.id.list);
        
        String[] cursorFields = new String[] { "name", "position", "picture" };
        int[] viewFields = new int[] { R.id.speaker_name, R.id.speaker_position, R.id.speaker_picture };
        speakersCursor = JG13Data.getSpeakersCursor();
        
        list.setAdapter(new SimpleCursorAdapter(rootView.getContext(), R.layout.listitem_speakers, speakersCursor, cursorFields, viewFields, 0));
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                speakersCursor.moveToPosition(position);
                String cv = speakersCursor.getString(4); // Column 4 is cv's link
                if(cv != null){
                    Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(cv));
                    startActivity(browser);
                }  
            }
        });
        return rootView;
    }

}
