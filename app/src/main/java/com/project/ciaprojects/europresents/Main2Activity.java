package com.project.ciaprojects.europresents;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.ciaprojects.europresents.fragment.SectionsStatePageAdapter;
import com.project.ciaprojects.europresents.fragment.SorteosAcabadosFragment;
import com.project.ciaprojects.europresents.fragment.SorteosActivosFragment;
import com.project.ciaprojects.europresents.fragment.SorteosParticipadosFragment;
import com.project.ciaprojects.europresents.fragment.SorteosProximosFragment;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";

    private SectionsStatePageAdapter mSectionsStatePageAdapter;
    private ViewPager mViewPager;
    public FirebaseAuth firebaseAuth;
    private TextView tvUser;

    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;
    private Button btnNavFrag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: started");

        mSectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);

        firebaseAuth = FirebaseAuth.getInstance();
        tvUser = (TextView) findViewById(R.id.tv_user);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            tvUser.setText(user.getDisplayName());
        } else {
            goMainScreen();
        }
        btnNavFrag1 = (Button) this.findViewById(R.id.btnNavFrag1);
        btnNavFrag2 = (Button) this.findViewById(R.id.btnNavFrag2);
        btnNavFrag3 = (Button) this.findViewById(R.id.btnNavFrag3);
        btnNavFrag4 = (Button) this.findViewById(R.id.btnNavFrag4);
        Log.d(TAG, "onCreateView: started");

        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(Main2Activity.this, "Sorteos activos", Toast.LENGTH_SHORT).show();

                setViewPager(0);
            }
        });

        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(Main2Activity.this, "Sorteos proximos", Toast.LENGTH_SHORT).show();

                setViewPager(1);
            }
        });

        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(Main2Activity.this, "Sorteos participando", Toast.LENGTH_SHORT).show();

                setViewPager(2);
            }
        });

        btnNavFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(Main2Activity.this, "Sorteos acabados", Toast.LENGTH_SHORT).show();

                setViewPager(3);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SorteosActivosFragment(), "Sorteos Activos");
        adapter.addFragment(new SorteosProximosFragment(), "Sorteos Proximos");
        adapter.addFragment(new SorteosParticipadosFragment(), "Sorteos Participados");
        adapter.addFragment(new SorteosAcabadosFragment(), "Sorteos Acabados");
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
