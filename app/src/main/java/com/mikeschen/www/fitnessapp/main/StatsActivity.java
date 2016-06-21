package com.mikeschen.www.fitnessapp.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.mikeschen.www.fitnessapp.Calories;
import com.mikeschen.www.fitnessapp.DatabaseCalorieListAdapter;
import com.mikeschen.www.fitnessapp.DatabaseHelper;
import com.mikeschen.www.fitnessapp.DatabaseStepsListAdapter;
import com.mikeschen.www.fitnessapp.R;
import com.mikeschen.www.fitnessapp.Steps;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StatsActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.stepsRecyclerView) RecyclerView mStepsRecyclerView;
    @Bind(R.id.calorieRecyclerView) RecyclerView mCaloriesRecyclerView;
    @Bind(R.id.button) Button mButton;
    private DatabaseStepsListAdapter mDatabaseStepsListAdapter;
    private DatabaseCalorieListAdapter mDatabaseCalorieListAdapter;
    public ArrayList<Steps> mSteps = new ArrayList<>();
    public ArrayList<Calories> mCalories = new ArrayList<>();
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ButterKnife.bind(this);

        db = new DatabaseHelper(getApplicationContext());
        mSteps = (ArrayList<Steps>) db.getAllStepRecords();
        mCalories = (ArrayList<Calories>) db.getAllCaloriesBurnedRecords();

        mDatabaseStepsListAdapter = new DatabaseStepsListAdapter(getApplicationContext(), mSteps);
        mStepsRecyclerView.setAdapter(mDatabaseStepsListAdapter);
        RecyclerView.LayoutManager stepsLayoutManager =
                new LinearLayoutManager(StatsActivity.this);
        mStepsRecyclerView.setLayoutManager(stepsLayoutManager);
        mStepsRecyclerView.setHasFixedSize(true);

        mDatabaseCalorieListAdapter = new DatabaseCalorieListAdapter(getApplicationContext(), mCalories);
        mCaloriesRecyclerView.setAdapter(mDatabaseCalorieListAdapter);
        RecyclerView.LayoutManager calorieLayoutManager =
                new LinearLayoutManager(StatsActivity.this);
        mCaloriesRecyclerView.setLayoutManager(calorieLayoutManager);
        mCaloriesRecyclerView.setHasFixedSize(true);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case(R.id.button) :
                db.deleteAllStepsRecords();
                db.deleteAllCaloriesBurnedRecords();
                refresh();
        }
    }

    public void refresh() {
        Intent intent = new Intent(StatsActivity.this, StatsActivity.class);
        startActivity(intent);
        finish();
    }
}




