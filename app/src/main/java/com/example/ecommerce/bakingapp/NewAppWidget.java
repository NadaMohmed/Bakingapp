package com.example.ecommerce.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        sharedPreference sh = new sharedPreference(context);


        ArrayList<StringBuilder> str = sh.getArrayList(sharedPreference.recipename);
        StringBuilder stringBuilder = new StringBuilder();
        if (str != null)

        {       for (int i = 0; i < str.size(); i++) {
                stringBuilder.append(sharedPreference.recipename + "\n" + str.get(i));

            }


        // views.setTextViewText(R.id.appwidget_text, JsonUtils.recipename);
        views.setTextViewText(R.id.appwidget_text, stringBuilder.toString());
    }
/*
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);


*/

/*
             views.setTextViewText(R.id.appwidget_text, JsonUtils.recipe_ingredients);

        Intent intent = new Intent(context, Detail.class);
        ArrayList<Recipe> li = (ArrayList<Recipe> )JsonUtils.listofrecipe ;
        intent.putParcelableArrayListExtra("steplist",li);
        intent.putExtra("step_item", li.get(0));
        intent.putExtra("position", 0);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
*/
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

