package es.ucm.jorngeren13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foound.widget.AmazingListView;

import es.ucm.jorngeren13.util.AmazingSimpleCursorAdapter;

public final class AgendaFragment extends JG13Fragment {

    /** List containing the agenda */
    private AmazingListView list;


    @Override
    public View onCreateView (final LayoutInflater inflater, final ViewGroup container, final Bundle saved)
    {
        final View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
        list = (AmazingListView) rootView.findViewById(R.id.list);
        
        String[] cursorFields = new String[] { "day", "start_time", "title", "description" };
        int[] viewFields = new int[] { R.id.day, R.id.start_time, R.id.title, R.id.description };
        
        list.setPinnedHeaderView(getLayoutInflater(saved).inflate(R.layout.listitem_agenda_header, list, false));
        list.setAdapter(new AmazingSimpleCursorAdapter(rootView.getContext(), R.layout.listitem_agenda, JG13Data.getAgendaCursor(), "day", cursorFields, viewFields, 0));
        
        return rootView;
    }
}
