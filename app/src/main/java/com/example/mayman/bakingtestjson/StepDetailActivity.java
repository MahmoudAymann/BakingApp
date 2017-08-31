package com.example.mayman.bakingtestjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class StepDetailActivity extends AppCompatActivity implements StepDetailFragment.OnFragmentInteractionListener {
    ArrayList<StepObjs> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_step_detail);

    }

    @Override
    public void onFragmentInteraction(Object uri) {

    }
}
