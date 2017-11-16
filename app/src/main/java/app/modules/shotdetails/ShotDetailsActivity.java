package app.modules.shotdetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.guilhermecardoso.shotstest.R;

/**
 * Created by guilhermecardoso on 11/14/17.
 */

public class ShotDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_SHOT_ID = "shotId";
    public static final String TAG = "ShotDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_details);

        setupFragment();
    }

    private void setupFragment() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        Bundle extras = getIntent().getExtras();

        if (fragment == null && extras != null && extras.containsKey(EXTRA_SHOT_ID)) {
            fragment = ShotDetailsFragment.newInstance(extras.getLong(EXTRA_SHOT_ID));
            ShotDetailsContract.Actions presenter = new ShotDetailsPresenter((ShotDetailsFragment) fragment);
            ((ShotDetailsFragment) fragment).setPresenter(presenter);
        }

        fragmentTransaction.replace(R.id.fragment, fragment, TAG);
        fragmentTransaction.commit();
    }
}
