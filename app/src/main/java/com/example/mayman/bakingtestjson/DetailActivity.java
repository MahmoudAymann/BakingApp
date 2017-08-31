package com.example.mayman.bakingtestjson;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.mayman.bakingtestjson.Adapters.DetailViewAdapter;
import com.example.mayman.bakingtestjson.Widget.WidgetProvider;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailActivity extends AppCompatActivity implements DetailViewAdapter.OnItemClickListener {

    @InjectView(R.id.detail_recyclerView) RecyclerView recyclerView;
    @InjectView(R.id.ingredients_id) TextView textView;

    DetailViewAdapter adapter;
    ArrayList<StepObjs> stepsData;
    public static ArrayList<String> ingredData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.inject(this);

        ingredData = getIntent().getStringArrayListExtra("ingred");
        stepsData = getIntent().getParcelableArrayListExtra("steps");

        for (int i = 0; i < stepsData.size(); i++) {
            Log.v("aym", stepsData.get(i).getShortDescription());
        }
        if (ingredData != null) {
            for (int i = 0; i < ingredData.size(); i++) {
                textView.append(ingredData.get(i) + "\n");
            }
        }//end if
        setlast(textView.getText().toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailViewAdapter(stepsData, this);
        recyclerView.setAdapter(adapter);


        Intent intent = new Intent(this, WidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException ignored) {
        }

    }//end onCreate


    @Override
    public void onItemClick(int item) {
        Intent intent = new Intent(this, StepDetailActivity.class);
        intent.putParcelableArrayListExtra("list", stepsData);
        intent.putExtra("idx", item);
        startActivity(intent);
    }

    void setlast(String f) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("eta", f).apply();
    }
}//end class Detai
