package app.modules.shotslist;

import android.os.Bundle;
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

        ShotsListFragment fragment = ShotsListFragment.newInstance();
        fragment.setPresenter(new ShotsListPresenter(fragment));
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }





}
