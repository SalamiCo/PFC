package es.ucm.jorngeren13;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class EventDetailsFragment extends JG13Fragment {

    private Button detailsInfo1;
    private Button detailsInfo2;
    String latLng1 = "40.424979,-3.707513";
    String latLng2 = "40.440,-3.729";

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_event_details, container, false);

        detailsInfo1 = (Button) rootView.findViewById(R.id.details_button_info_1);
        detailsInfo2 = (Button) rootView.findViewById(R.id.details_button_info_2);
        detailsInfo1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick (View v) {
                launchMap(latLng1, "Paraninfo de la UCM");
            }
        });

        detailsInfo2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick (View v) {
                launchMap(latLng2, "Museo del Traje");
            }
        });
        return rootView;
    }

    private void launchMap (String latLng, String label) {
        Uri geoUri = Uri.parse("geo:" + latLng + "?q=" + latLng + "(" + label + ")");
        Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
        if (getActivity().getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            Toast.makeText(getActivity(), R.string.toast_error_map, Toast.LENGTH_SHORT).show();

        } else {
            startActivity(intent);

        }
    }

}
