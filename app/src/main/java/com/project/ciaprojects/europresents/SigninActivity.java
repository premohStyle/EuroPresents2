package com.project.ciaprojects.europresents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class SigninActivity extends AppCompatActivity {

    private static final int SIGNIN = 1;
    private static final String TAG = "SigninActivity";
    private Button buttonPrev, buttonNext;
    private ViewAnimator viewAnimator;
    private Animation slide_in_left, slide_out_right, slide_in_right, slide_out_left;
    private short currentPage;
    private EditText etUser, etPassword, etCPassword, etName, etLastname, etEmail;
    private LinearLayout contentLayout;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        this.currentPage = 1;

        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        buttonPrev = (Button) findViewById(R.id.button_back);
        buttonNext = (Button) findViewById(R.id.button_next);
        viewAnimator = (ViewAnimator) findViewById(R.id.view_animator);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etCPassword = (EditText) findViewById(R.id.et_cpassword);
        etName = (EditText) findViewById(R.id.et_name);
        etLastname = (EditText) findViewById(R.id.et_lastname);
        etUser = (EditText) findViewById(R.id.et_user);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    goMainWindow();
                }
            }
        };


        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        Spinner citizenship = (Spinner) findViewById(R.id.input_citizenship);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        citizenship.setAdapter(adapter);

        slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        slide_in_right = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slide_out_left = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);

        buttonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (currentPage != 1) {
                    viewAnimator.setInAnimation(slide_in_left);
                    viewAnimator.setOutAnimation(slide_out_right);
                    viewAnimator.showPrevious();
                    currentPage--;
                    if (currentPage == 2) {
                        buttonNext.setText(R.string.next);
                    }
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (validateForm() && currentPage != 3) {
                    viewAnimator.setInAnimation(slide_in_right);
                    viewAnimator.setOutAnimation(slide_out_left);
                    viewAnimator.showNext();
                    currentPage++;
                    if (currentPage == 3) {
                        buttonNext.setText(R.string.send);
                    }
                } else {
                    contentLayout.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    registrarUsuario();
                }
            }
        });


    }

    private void registrarUsuario() {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            final FirebaseUser user = firebaseAuth.getCurrentUser();
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SigninActivity.this,
                                                "Verification email sent to " + user.getEmail(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.e(TAG, "sendEmailVerification", task.getException());
                                        Toast.makeText(SigninActivity.this,
                                                "Failed to send verification email.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            goMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        ;
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;
        if (this.currentPage == 1) {
            if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.validation_empty_fields, Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (etPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.validation_empty_fields, Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (etCPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.validation_empty_fields, Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (!etPassword.getText().toString().equals(etCPassword.getText().toString())) {
                Toast.makeText(this, R.string.validation_password_not_match, Toast.LENGTH_SHORT).show();
                valid = false;
            }
        } else if (this.currentPage == 2) {
            if (etUser.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.validation_empty_fields, Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (etName.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.validation_empty_fields, Toast.LENGTH_SHORT).show();
                valid = false;
            } else if (etLastname.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.validation_empty_fields, Toast.LENGTH_SHORT).show();
                valid = false;
            }
        } else if (this.currentPage == 3) {
            valid = false;
        } else {
            valid = false;
        }
        return valid;
    }

    private void goMainWindow() {
        Intent intent = new Intent(this, MainWindowActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
