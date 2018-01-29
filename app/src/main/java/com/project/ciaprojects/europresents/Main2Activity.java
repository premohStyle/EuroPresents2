package com.project.ciaprojects.europresents;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.project.ciaprojects.europresents.fragment.SectionsStatePageAdapter;
import com.project.ciaprojects.europresents.fragment.SorteosActivosFragment;
import com.project.ciaprojects.europresents.fragment.SorteosParticipadosFragment;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";

    private SectionsStatePageAdapter mSectionsStatePageAdapter;
    private ViewPager mViewPager;
    public FirebaseAuth firebaseAuth;
    public FirebaseStorage storage;
    private TextView tvUser;

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: started");

        mSectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);

        firebaseAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        //tvUser = (TextView) findViewById(R.id.tv_user);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            //tvUser.setText(user.getDisplayName());
        } else {
            goMainScreen();
        }

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SorteosActivosFragment(), "Sorteos Activos");
        adapter.addFragment(new SorteosParticipadosFragment(), "Sorteos Participados");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
