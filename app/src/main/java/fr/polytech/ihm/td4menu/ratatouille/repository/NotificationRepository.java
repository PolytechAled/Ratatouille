package fr.polytech.ihm.td4menu.ratatouille.repository;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class NotificationRepository extends Application {
    public static final String CHANNEL_1_ID = "channel LOW";
    public static final String CHANNEL_2_ID = "channel DEFAULT";
    public static final String CHANNEL_3_ID = "channel HIGH";
    private static NotificationManager notificationManager;



    private NotificationChannel createNotificationChannel(String channelId, CharSequence name, int importance, String channelDescription) {
        // Créer le NotificationChannel, seulement pour API 26+
        NotificationChannel channel = new NotificationChannel(channelId, name, importance);
        channel.setDescription(channelDescription);
        return channel;
    }


    private void createNotificationChannels() {
        NotificationChannel channel1 = createNotificationChannel(CHANNEL_1_ID,"Channel 1",
                NotificationManager.IMPORTANCE_LOW,"This Channel has low priority");
        NotificationChannel channel2 = createNotificationChannel(CHANNEL_2_ID,"Channel 2",
                NotificationManager.IMPORTANCE_DEFAULT,"This Channel has default priority");
        NotificationChannel channel3 = createNotificationChannel(CHANNEL_3_ID,"Channel 2",
                NotificationManager.IMPORTANCE_HIGH,"This Channel has high priority");

        NotificationManager manager = getSystemService(NotificationManager.class);
        Objects.requireNonNull(manager).createNotificationChannel(channel1);
        Objects.requireNonNull(manager).createNotificationChannel(channel2);
        Objects.requireNonNull(manager).createNotificationChannel(channel3);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }
}

