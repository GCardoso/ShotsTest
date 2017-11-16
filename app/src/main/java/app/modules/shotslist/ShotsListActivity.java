package app.modules.shotslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.guilhermecardoso.shotstest.R;

public class ShotsListActivity extends AppCompatActivity {

    private static final String TAG = "ShotsListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragment();
    }

    private void setupFragment() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = ShotsListFragment.newInstance();
            ShotsListPresenter presenter = new ShotsListPresenter((ShotsListFragment) fragment);
            ((ShotsListFragment) fragment).setPresenter(presenter);
        }

        fragmentTransaction.replace(R.id.fragment, fragment, TAG);
        fragmentTransaction.commit();
    }
}
