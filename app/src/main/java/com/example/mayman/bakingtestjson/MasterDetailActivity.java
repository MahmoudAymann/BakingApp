package com.example.mayman.bakingtestjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MasterDetailActivity extends AppCompatActivity implements StepDetailFragment.OnFragmentInteractionListener, StepsIngredientsFragment.OnFragmentInteractionListener {
    StepDetailFragment stepDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);

        stepDetailFragment = (StepDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    @Override
    public void onFragmentInteraction(Object uri) {
    }


    @Override
    public void onFragmentInteractionde(int uri) {
        stepDetailFragment.solv(uri);
    }
}
