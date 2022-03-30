package fr.polytech.ihm.td4menu.ratatouille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;

import java.util.Calendar;

import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.repository.CalendarRepository;

public class MenuGenerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_generator);

        findViewById(R.id.calendar_button).setOnClickListener((evt) -> {
            CalendarRepository cal = new CalendarRepository();

            Calendar beginTime = Calendar.getInstance();
            beginTime.set(2022, 8, 1, 7, 30);

            cal.registerEvent(this, new Recipe("Porc au caramel", 2), beginTime);
        });
    }
}