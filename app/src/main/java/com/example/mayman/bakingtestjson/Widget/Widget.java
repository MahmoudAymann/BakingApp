package com.example.mayman.bakingtestjson.Widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mayman.bakingtestjson.R;

import java.util.ArrayList;

import static com.example.mayman.bakingtestjson.DetailActivity.ingredData;

/**
 * Created by MahmoudAyman on 8/26/2017.
 */

public class Widget implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private ArrayList<String> stringArrayList = new ArrayList<>();

    public Widget(Context context, Intent intent) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        stringArrayList.clear();

        if (ingredData != null) {
            for (int i = 0; i < ingredData.size(); i++) {
                Log.v("hom", ingredData.size() + "size");
                String ingredient = ingredData.get(i) + "\n";
                stringArrayList.add(ingredient);
            }
        }
    }//end onDataSetChanged()

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (stringArrayList == null || stringArrayList.size() == 0) {
            return 0;
        } else {
            return stringArrayList.size();
        }
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public static class Service extends RemoteViewsService {
        @Override
        public RemoteViewsFactory onGetViewFactory(Intent intent) {
            return new Widget(this, intent);
        }
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item_view);
        remoteViews.setTextViewText(R.id.widget_text_id, stringArrayList.get(position));
        return remoteViews;
    }//end remote

}//end class Widget