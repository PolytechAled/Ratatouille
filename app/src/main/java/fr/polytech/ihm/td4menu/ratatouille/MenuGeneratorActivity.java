package fr.polytech.ihm.td4menu.ratatouille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;

import java.util.Calendar;

import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.repository.CalendarRepository;

public class MenuGeneratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_generator);
    }
}