package es.ucm.mgrcongress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;

public class AgendaActivity extends MgrActivity implements ActionBar.OnNavigationListener {

    /**
     * The serialization (saved instance state) Bundle key representing the current dropdown position.
     */
    private static final String STATE_SELECTED_NAVITEM = "selected_navitem";

    @Override
    protected void onCreate (final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(new ArrayAdapter<String>(
            actionBar.getThemedContext(), android.R.layout.simple_list_item_1, android.R.id.text1, new String[] {
                getString(R.string.title_section_agenda), getString(R.string.title_section_speakers),
                getString(R.string.title_section_details), }), this);
    }

    @Override
    public void onRestoreInstanceState (final Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVITEM)) {
            getSupportActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVITEM));
        }
    }

    @Override
    public void onSaveInstanceState (final Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVITEM, getSupportActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu (final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.agenda, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected (final int position, final long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.
        final Fragment fragment = new DummySectionFragment();
        
        final Bundle args = new Bundle();
        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        
        return true;
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment () {
        }

        @Override
        public View onCreateView (
            final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
        {
            final View rootView = inflater.inflate(R.layout.fragment_agenda_dummy, container, false);
            final TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

}
