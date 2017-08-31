package com.example.mayman.bakingtestjson.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.mayman.bakingtestjson.R;

/**
 * Created by MahmoudAyman on 8/25/2017.
 */

public class WidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setRemoteAdapter(R.id.widget_grid_view_id, new Intent(context, Widget.Service.class));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }//end updateAppWidget

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int ids : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, ids);
        }
    }//end onUpdate

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, WidgetProvider.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName), R.id.widget_grid_view_id);
            this.onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(componentName));
        }//end if
    }//end onReceive

}//end WidgetProvider