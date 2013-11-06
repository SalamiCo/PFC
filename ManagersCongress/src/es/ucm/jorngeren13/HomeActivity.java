package es.ucm.jorngeren13;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;

public class HomeActivity extends JG13Activity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

    /** The serialization (saved instance state) Bundle key representing the current dropdown position. */
    private static final String STATE_SELECTED_NAVITEM = "selected_navitem";

    private ActionBar actionBar;
    private ViewPager viewPager;

    private final Map<Class<? extends Fragment>,Fragment> fragments = new HashMap<Class<? extends Fragment>,Fragment>(
        8, 0.8f);

    @Override
    protected void onCreate (final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(this);

        // Set up the action bar to show a dropdown list.
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the dropdown list navigation in the action bar.
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section_agenda).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section_speakers).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section_details).setTabListener(this));
    }

    @Override
    public void onRestoreInstanceState (final Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVITEM)) {
            int position = savedInstanceState.getInt(STATE_SELECTED_NAVITEM);
            actionBar.setSelectedNavigationItem(position);
            viewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onSaveInstanceState (final Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVITEM, getSupportActionBar().getSelectedNavigationIndex());
    }

    @Override
    public void onTabSelected (Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected (Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected (Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onPageScrollStateChanged (int state) {
        
    }

    @Override
    public void onPageScrolled (int arg0, float arg1, int arg2) {
        
    }

    @Override
    public void onPageSelected (int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    private final class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem (int position) {
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
                    fragClass = EventDetailsFragment.class;
                    break;
                }
            }

            Fragment fragment = fragments.get(fragClass);
            if (fragment == null) {
                fragment = Fragment.instantiate(HomeActivity.this, fragClass.getName());
                fragments.put(fragClass, fragment);
            }

            return fragment;
        }

        @Override
        public int getCount () {
            return 3;
        }

    }
}
