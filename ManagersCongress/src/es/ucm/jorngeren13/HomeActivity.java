package es.ucm.jorngeren13;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;

public class HomeActivity extends JG13Activity implements ActionBar.OnNavigationListener {

    /** The serialization (saved instance state) Bundle key representing the current dropdown position. */
    private static final String STATE_SELECTED_NAVITEM = "selected_navitem";

    private static final Map<Class<? extends Fragment>,Fragment> fragments =
        new HashMap<Class<? extends Fragment>,Fragment>(8, 0.8f);

    @Override
    protected void onCreate (final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agenda);

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
        Class<? extends Fragment> fragClass = null;

        switch (position) {
            case 0: {
                fragClass = AgendaFragment.class;
                break;
            }
            case 1: {
                fragClass = SpeakersFragment.class;
                break;
            }
            case 2: {
            //    fragClass = EventDetailsFragment.class;
                break;
            }
        }

        Fragment fragment = fragments.get(fragClass);
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fragClass.getName());
            fragments.put(fragClass, fragment);
        }
        
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        return true;
    }
}
