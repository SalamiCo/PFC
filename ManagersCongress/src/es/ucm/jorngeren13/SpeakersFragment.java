package es.ucm.jorngeren13;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SpeakersFragment extends JG13Fragment {

    /** List containing the speakers **/
    private ListView list;
    private Cursor speakersCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	final View rootView = inflater.inflate(R.layout.fragment_speakers, container, false);
	list = (ListView) rootView.findViewById(R.id.list);

	String[] cursorFields = new String[] { "name", "position", "picture" };
	int[] viewFields = new int[] { R.id.speaker_name, R.id.speaker_position, R.id.speaker_picture };
	speakersCursor = JG13Data.getSpeakersCursor();

	list.setAdapter(new SimpleCursorAdapter(rootView.getContext(), R.layout.listitem_speakers, speakersCursor,
		cursorFields, viewFields, 0));
	list.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		showCv(position);
	    }
	});
	return rootView;
    }

    private void showCv(int position) {
	speakersCursor.moveToPosition(position);
	final String cv = speakersCursor.getString(4); // Column 4 is cv's link

	if (cv == null) {
	    Toast.makeText(getActivity(), R.string.toast_no_cv, Toast.LENGTH_LONG).show();

	} else {
	    // If not connected to Wifi, show an information message
	    ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

	    if (activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
		    && activeNetwork.isConnected()) {

		Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(cv));
		startActivity(browser);

	    } else {

		new AlertDialog.Builder(getActivity()).setTitle(R.string.dialog_title)
			.setMessage(R.string.dialog_message)
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
				Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(cv));
				startActivity(browser);
			    }
			}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
				// Do nothing
			    }
			}).show();
	    }

	}
    }

}
