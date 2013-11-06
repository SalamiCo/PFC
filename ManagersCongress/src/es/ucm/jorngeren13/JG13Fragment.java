package es.ucm.jorngeren13;

import android.app.Activity;

import com.actionbarsherlock.app.SherlockFragment;

public abstract class JG13Fragment extends SherlockFragment {

    private JG13Activity activity;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof JG13Activity)) {
            throw new IllegalStateException(activity.getClass().getName() + " !instanceof "
                + JG13Activity.class.getSimpleName());
        }

        this.activity = (JG13Activity) activity;
    }
    
    protected JG13Activity getInternalActivity () {
        return activity;
    }
}
