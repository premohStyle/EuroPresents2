package com.project.ciaprojects.europresents;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.project.ciaprojects.europresents.fragment.SectionsStatePageAdapter;
import com.project.ciaprojects.europresents.fragment.SorteosAcabadosFragment;
import com.project.ciaprojects.europresents.fragment.SorteosActivosFragment;
import com.project.ciaprojects.europresents.fragment.SorteosParticipadosFragment;
import com.project.ciaprojects.europresents.fragment.SorteosProximosFragment;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";

    private SectionsStatePageAdapter mSectionsStatePageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: started");

        mSectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SorteosActivosFragment(), "Sorteos Activos");
        adapter.addFragment(new SorteosProximosFragment(), "Sorteos Proximos");
        adapter.addFragment(new SorteosParticipadosFragment(), "Sorteos Participados");
        adapter.addFragment(new SorteosAcabadosFragment(), "Sorteos Acabados");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
