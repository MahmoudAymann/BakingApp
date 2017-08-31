package com.example.mayman.bakingtestjson;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mayman.bakingtestjson.Adapters.RecyclerMainAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements listner1 {

    @InjectView(R.id.main_recycler_id) RecyclerView recyclerView;

    RecyclerMainAdapter recyclerMainAdapter;

    final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        if (isNetworkAvailable()) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
            updateTask(URL); //active Async
        } else
            Toast.makeText(this, "You are not online!", Toast.LENGTH_SHORT).show();


    }//end onCreate


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void updateTask(String url) {
        BakingAsyncTask bakingAsyncTask = new BakingAsyncTask(this);
        bakingAsyncTask.execute(url);
    }

    @Override
    public void onTaskComplete(final List<?> result) {

        recyclerMainAdapter = new RecyclerMainAdapter((List<BakingObjs>) result, new RecyclerMainAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BakingObjs item) {
                if (item == null) {
                    Toast.makeText(MainActivity.this, "no items found", Toast.LENGTH_SHORT).show();
                } else
                    call(item);
            }
        });

        recyclerView.setAdapter(recyclerMainAdapter);
        recyclerMainAdapter.notifyDataSetChanged();
    }

    void call(BakingObjs item) {
        Configuration configuration = this.getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;

        if (screenWidthDp >= 600) {
            Intent intent = new Intent(MainActivity.this, MasterDetailActivity.class);

            intent.putStringArrayListExtra("ingred", item.getIngrediantList());
            intent.putParcelableArrayListExtra("steps", item.getStepObjsList());
            intent.putParcelableArrayListExtra("list", item.getStepObjsList());
            intent.putExtra("idx", 0);

            for (int i = 0; i < item.getStepObjsList().size(); i++) {
                Log.v("aymxer78", item.getStepObjsList().get(i).getShortDescription());
            }
            startActivity(intent);

        } else {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            intent.putStringArrayListExtra("ingred", item.getIngrediantList());
            intent.putParcelableArrayListExtra("steps", item.getStepObjsList());
            for (int i = 0; i < item.getStepObjsList().size(); i++) {
                Log.v("aymxer78", item.getStepObjsList().get(i).getShortDescription());
            }
            startActivity(intent);
        }

    }
}//END CLASS MainActivity