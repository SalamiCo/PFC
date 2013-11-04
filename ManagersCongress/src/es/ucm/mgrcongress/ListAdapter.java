package es.ucm.mgrcongress;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private Cursor myCursor;
    private LayoutInflater myInflater;
    
    public ListAdapter(Context context, Cursor cursor){
        //Optimization
        myInflater = LayoutInflater.from(context);
        this.myCursor = cursor;
        this.myCursor.moveToFirst();
    }
    @Override
    public int getCount () {
        return myCursor.getCount();
    }

    @Override
    public Object getItem (int position) {
        //TODO Return the entire row. How?
        return myCursor;
    }

    @Override
    public long getItemId (int position) {
        // _id is the first column in the cursor
        return myCursor.getInt(0);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        
        // Create a new view if is not created before
        if(convertView == null){
            convertView = myInflater.inflate(R.layout.agenda_list_row, null);
            holder = new ViewHolder();
            holder.holderDay = (TextView) convertView.findViewById(R.id.day);
            holder.holderStartTime = (TextView) convertView.findViewById(R.id.start_time);
            holder.holderTitle = (TextView) convertView.findViewById(R.id.title);
            holder.holderDescription = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);
            
        } else holder = (ViewHolder) convertView.getTag();
        
        // FIXME Maybe exists a better way to do this
        // _id: 0 title:1 description:2 day:3 start_time:4
        holder.holderDay.setText(myCursor.getString(3));
        holder.holderStartTime.setText(myCursor.getString(4));
        holder.holderTitle.setText(myCursor.getString(1));
        holder.holderDescription.setText(myCursor.getString(2));
        if(!myCursor.isLast()) myCursor.moveToNext();
        
        return convertView;
    }

    class ViewHolder {
        // The elements in the layout list_row.xml
        TextView holderDay;
        TextView holderStartTime;
        TextView holderTitle;
        TextView holderDescription;
    }
}
