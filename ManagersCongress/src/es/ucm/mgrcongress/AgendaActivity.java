package es.ucm.mgrcongress;


import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;

public class AgendaActivity extends MgrActivity implements ActionBar.OnNavigationListener {

    /**
     * The serialization (saved instance state) Bundle key representing the current dropdown position.
     */
    private static final String STATE_SELECTED_NAVITEM = "selected_navitem";
    
    private static MatrixCursor agendaCursor;

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
        
        // Data for the agenda
        agendaCursor = new MatrixCursor(new String[]{"_id", "title", "description", "day", "start_time"});
        //First day
        agendaCursor.addRow(new Object[]{1, "Recepción de los asistentes y entrega de documentación y acreditaciones.", null, "11 Nov", "12:00"});
        agendaCursor.addRow(new Object[]{2, "Inauguración de las XXXI Jornadas de Gerencia Universitaria.", null, "11 Nov", "13:00"});
        agendaCursor.addRow(new Object[]{3, "Conferencia: Reinventando la toma de decisiones en la Universidad.", "José Ramón Chaves García. Magistrado de lo contencioso-administrativo.", "11 Nov", "13:15"});
        agendaCursor.addRow(new Object[]{4, "Cóctel en el Paraninfo.", null, "11 Nov", "14:15"});
        agendaCursor.addRow(new Object[]{5, "Constitución de los grupos de trabajo en librerías emblemáticas de Madrid: La Central, Ocho y Medio y Tipos Infames.", null, "11 Nov", "17:00"});
        agendaCursor.addRow(new Object[]{6, "Cóctel en el Palacio de Cibeles (sede del Ayuntamiento de Madrid)", null, "11 Nov", "21:30"});
        //Second day
        agendaCursor.addRow(new Object[]{7, "Mesa redonda: Reducción de costes y optimización de recursos.", "Moderador: Teodoro Conde Minaya. Gerente UAM.", "12 Nov", "09:00"});
        agendaCursor.addRow(new Object[]{8, " Pausa-café.", null, "12 Nov", "11:00"});
        agendaCursor.addRow(new Object[]{9, "Talleres.", null, "12 Nov", "11:30"});
        agendaCursor.addRow(new Object[]{10, "Mesa redonda: Alternativas a la financiación pública y captación de fondos.", "Moderador: Jordi Montserrat Garrocho. Gerente UNED.", "12 Nov", "12:30"});
        agendaCursor.addRow(new Object[]{11, "Almuerzo en el Museo del Traje.", null, "12 Nov", "14:30"});
        agendaCursor.addRow(new Object[]{12, "Conferencia: Gerencia en la Universidad: Visión en Acción.", "Javier Oliva López", "12 Nov", "16:30"});
        agendaCursor.addRow(new Object[]{13, "Mesa de gerentes.", null, "12 Nov", "17:30"});
        agendaCursor.addRow(new Object[]{14, "Cena institucional.", null, "12 Nov", "21:30"});
        //Third day
        agendaCursor.addRow(new Object[]{15, "Conferencia: \"Educación universitaria y Fundaciones americanas\"", "Eelco Keij. Especialista en fundraising. Fundador de KeyLance Consultancy LLC en Nueva York.", "13 Nov", "09:30"});
        agendaCursor.addRow(new Object[]{16, "Talleres.", null, "13 Nov", "10:30"});
        agendaCursor.addRow(new Object[]{17, "Pausa-café.", null, "13 Nov", "11:30"});
        agendaCursor.addRow(new Object[]{18, "Mesa redonda: Gestión de personas en tiempos de crisis. Nuevos enfoques en la gestión de los recursos humanos.", "Moderadora: Carmen García Elias. Gerente UPM", "13 Nov", "12:00"});
        agendaCursor.addRow(new Object[]{19, "Clausura de las jornadas.", null, "13 Nov", "14:00"});
        agendaCursor.addRow(new Object[]{20, "Cóctel de despedida.", null, "13 Nov", "14:15"});
        
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
        final Fragment fragment = new AgendaSectionFragment();
        
        /*final Bundle args = new Bundle();
        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);*/
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        
        return true;
    }

    public static class AgendaSectionFragment extends Fragment {
        /**
         * The fragment argument representing the Agenda.
         */

        private ListView list;

        public AgendaSectionFragment () {
        }

        @Override
        public View onCreateView (
            final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
        {
            final View rootView = inflater.inflate(R.layout.activity_agenda, container, false);
            list = (ListView) rootView.findViewById(R.id.list);
            String[] cursorFields = new String[] {"day", "start_time", "title", "description" };
            int[] viewFields = new int[]{R.id.day, R.id.start_time, R.id.title, R.id.description};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(rootView.getContext(),R.layout.list_row,
                                                                AgendaActivity.agendaCursor,cursorFields,viewFields,0);
            list.setAdapter(adapter);
            return rootView;
        }
    }

}
