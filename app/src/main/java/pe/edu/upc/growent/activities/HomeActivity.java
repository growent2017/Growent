package pe.edu.upc.growent.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import pe.edu.upc.growent.R;
import pe.edu.upc.growent.fragments.ExpensesFragment;
import pe.edu.upc.growent.fragments.HomeFragment;
import pe.edu.upc.growent.fragments.OtherCategory;
import pe.edu.upc.growent.fragments.SettingFragment;
import pe.edu.upc.growent.models.User;

public class HomeActivity extends AppCompatActivity {
    User user;
    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  return navigateAccordingTO(item.getItemId());
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = User.fromBundle(getIntent().getExtras());
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateAccordingTO(R.id.navigation_home);
    }

    private boolean navigateAccordingTO(int id){
        try{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content,getFragmentFor(id))
                    .commit();

            return true;

        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private Fragment getFragmentFor(int id)
    {
        switch(id)
        {
            case R.id.navigation_home: return new HomeFragment();
            case R.id.navigation_expenses: return new ExpensesFragment();
            case R.id.navigation_settings: return new SettingFragment();
        }
        return null;

    }


}
