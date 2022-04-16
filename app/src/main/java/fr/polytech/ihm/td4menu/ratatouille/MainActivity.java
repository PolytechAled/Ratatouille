package fr.polytech.ihm.td4menu.ratatouille;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.polytech.ihm.td4menu.ratatouille.Application.Notifications.CHANNEL_2_ID;
import static fr.polytech.ihm.td4menu.ratatouille.Application.Notifications.CHANNEL_1_ID;
import static fr.polytech.ihm.td4menu.ratatouille.Application.Notifications.CHANNEL_3_ID;
import org.json.JSONException;

import java.io.IOException;

import fr.polytech.ihm.td4menu.ratatouille.datas.DataSource;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipe;
import fr.polytech.ihm.td4menu.ratatouille.datas.Recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.polytech.ihm.td4menu.ratatouille.recipe.ListRecipeActivity;

public class MainActivity extends AppCompatActivity {

    private int notificationId = 0;

    private void sendNotificationOnChannel(String title, String content, String channelId, int priority) {

        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pintent = PendingIntent.getActivity(this, 0,
                intent, 0);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText("id=" + ++notificationId + " - " + content)
                .setPriority(priority)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pintent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.img))
                    .bigLargeIcon(null))
                .setAutoCancel(true);

        NotificationManagerCompat.from(this).notify( notificationId, notification.build() );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ListRecipeActivity.class);
        startActivity(intent);

        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH");
        String s = f.format(d);
        TextView printheure = findViewById(R.id.printheure);
        printheure.setText(s);

        if(Integer.parseInt(s) == 16 || Integer.parseInt(s) == 11) sendNotificationOnChannel("Au fourneau !", "C'est de préparer le plat: {}", CHANNEL_3_ID, NotificationCompat.PRIORITY_HIGH);

        new Thread(() -> {
            /*List<Integer> recipeList = new ArrayList<>();

            recipeList.add(716429);*/

            Recipe recipe = null;
            try {
                recipe = Recipe.instantiate(DataSource.SPOONACULAR, 716429);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            Log.d("Ratatouille", recipe.toString());

            Recipes.add(recipe);
        }).start();
    }
}