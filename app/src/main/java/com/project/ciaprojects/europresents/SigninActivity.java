package com.project.ciaprojects.europresents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class SigninActivity extends AppCompatActivity {

    private Button buttonPrev, buttonNext;
    private ViewAnimator viewAnimator;
    private Animation slide_in_left, slide_out_right, slide_in_right, slide_out_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        buttonPrev = (Button) findViewById(R.id.button_back);
        buttonNext = (Button) findViewById(R.id.button_next);
        viewAnimator = (ViewAnimator) findViewById(R.id.view_animator);

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

        buttonPrev.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                viewAnimator.setInAnimation(slide_in_left);
                viewAnimator.setOutAnimation(slide_out_right);
                viewAnimator.showPrevious();
            }});

        buttonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                viewAnimator.setInAnimation(slide_in_right);
                viewAnimator.setOutAnimation(slide_out_left);
                viewAnimator.showNext();
            }});


    }
}
