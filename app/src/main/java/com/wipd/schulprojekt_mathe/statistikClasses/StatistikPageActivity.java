package com.wipd.schulprojekt_mathe.statistikClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.wipd.schulprojekt_mathe.R;

public class StatistikPageActivity extends AppCompatActivity {

    Animation slideUpAnim, slideDownAnim;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


    public void testClick(View view) {
        Toast.makeText(this, "Some info here", Toast.LENGTH_SHORT).show();
    }

    public void openStatistikRechner_btnMaximum(View view) {
        Intent intent = new Intent(this, StatistikRechnerActivity.class);
        startActivity(intent);
    }
}
