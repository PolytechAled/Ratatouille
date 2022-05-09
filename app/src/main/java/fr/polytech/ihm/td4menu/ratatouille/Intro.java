package fr.polytech.ihm.td4menu.ratatouille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


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
                        //Intent intent = new Intent(getApplicationContext(), MenuGenerationActivity.class);
                        startActivity(intent);
                    }
                }, milliseconds);
            }
        });
    }
}