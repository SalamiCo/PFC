package es.ucm.jorngeren13;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public final class AgendaFragment extends JG13Fragment {

    /** List containing the agenda */
    private ListView list;


    @Override
    public View onCreateView (final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
        list = (ListView) rootView.findViewById(R.id.list);
        
        String[] cursorFields = new String[] { "day", "start_time", "title", "description" };
        int[] viewFields = new int[] { R.id.day, R.id.start_time, R.id.title, R.id.description };
        
        list.setAdapter(new SimpleCursorAdapter(rootView.getContext(), R.layout.listitem_agenda, JG13Data.getAgendaCursor(), cursorFields, viewFields, 0));
        
        return rootView;
    }
}
