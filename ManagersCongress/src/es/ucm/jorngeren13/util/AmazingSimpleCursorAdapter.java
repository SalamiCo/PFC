package es.ucm.jorngeren13.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.foound.widget.AmazingAdapter;

public class AmazingSimpleCursorAdapter extends AmazingAdapter {

    /** Internal SimpleCursorAdapter in charge of the inner workings */
    private final SimpleCursorAdapter simpleCursorAdapter;

    /** Sections of the list */
    private final List<Object> sections = new ArrayList<Object>();

    public AmazingSimpleCursorAdapter (
        Context context, int layout, Cursor cursor, String headerField, String[] from, int[] to, int flags)
    {
        simpleCursorAdapter = new SimpleCursorAdapter(context, layout, cursor, from, to, flags);

        // Obtain section headers
        Object section = null;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, values);
            Object obj = values.get(headerField);
            
            if ((section == null && obj != null) || (section != null && !section.equals(obj))){
                section = obj;
                sections.add(section);
            }
        }
    }

    @Override
    public int getCount () {
        return simpleCursorAdapter.getCount();
    }

    @Override
    public Object getItem (int position) {
        return simpleCursorAdapter.getItem(position);
    }

    @Override
    public long getItemId (int position) {
        return simpleCursorAdapter.getItemId(position);
    }

    @Override
    protected void onNextPageRequested (int page) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void bindSectionHeader (View view, int position, boolean displaySectionHeader) {
        View header = view.findViewById(es.ucm.jorngeren13.R.id.header);
        if (displaySectionHeader) {
            header.setVisibility(View.VISIBLE);

        } else {
            header.setVisibility(View.GONE);
        }
    }

    @Override
    public View getAmazingView (int position, View convertView, ViewGroup parent) {
        return simpleCursorAdapter.getView(position, convertView, parent);
    }

    @Override
    public void configurePinnedHeader (View header, int position, int alpha) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPositionForSection (int section) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSectionForPosition (int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object[] getSections () {
        return sections.toArray();
    }

}
