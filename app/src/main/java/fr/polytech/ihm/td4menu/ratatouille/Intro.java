package fr.polytech.ihm.td4menu.ratatouille;

import static fr.polytech.ihm.td4menu.ratatouille.Application.Notifications.CHANNEL_3_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.polytech.ihm.td4menu.ratatouille.recipe.ListRecipeActivity;

public class Intro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        delay(2);

        //intent = new Intent(this, ListRecipeActivity.class);
        //startActivity(intent);
    }

    public void delay(int seconds){
        int milliseconds = seconds * 1000;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("XXX");                 //add your code here
                        Intent intent = new Intent(getApplicationContext(), ListRecipeActivity.class);
                        startActivity(intent);
                    }
                }, milliseconds);
            }
        });
    }
}