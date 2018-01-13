package com.project.ciaprojects.europresents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainWindowActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Button logoutBt, revokeBt;
    TextView nameTv, mailTv, idTv;
    ImageView userIv;
    Intent intent;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        intent = this.getIntent();

        logoutBt = (Button) findViewById(R.id.logout_bt);
        revokeBt = (Button) findViewById(R.id.revoke_bt);
        nameTv = (TextView) findViewById(R.id.name_tv);
        mailTv = (TextView) findViewById(R.id.email_tv);
        idTv = (TextView) findViewById(R.id.id_tv);
        userIv = (ImageView) findViewById(R.id.user_iv);

        logoutBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String id = user.getUid();

            nameTv.setText(name);
            mailTv.setText(email);
            idTv.setText(id);

            Glide.with(this).load(user.getPhotoUrl()).into(userIv);

        } else {
            goMainScreen();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void logout() {
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        goMainScreen();
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
