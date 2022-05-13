package fr.polytech.ihm.td4menu.ratatouille.repository;

import android.app.Activity;
import android.content.Intent;
import android.provider.CalendarContract;

import java.util.Calendar;

import fr.polytech.ihm.td4menu.ratatouille.datas.recipe.Recipe;

public class CalendarRepository {

    public void registerEvent(Activity context, Recipe recipe, Calendar beginTime){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        intent.putExtra(CalendarContract.Events.TITLE, recipe.getName());
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        beginTime.add(Calendar.HOUR_OF_DAY, recipe.getCookingTime());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, beginTime.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.ALL_DAY, false);

        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Preparez votre repas");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "La cuisine");

        context.startActivity(intent);
    }

}
