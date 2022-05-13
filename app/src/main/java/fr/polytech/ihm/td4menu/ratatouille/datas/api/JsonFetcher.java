package fr.polytech.ihm.td4menu.ratatouille.datas.api;

import android.os.NetworkOnMainThreadException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class JsonFetcher {
    public static String fetch(URL url) throws IOException {
        StringBuilder builder = new StringBuilder();
        URLConnection connection = url.openConnection();

        connection.setRequestProperty("User-Agent", "Ratatouille Android App");
        connection.setRequestProperty("Accept", "application/json");

        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine()).append('\n');
            }
        } catch (NetworkOnMainThreadException e){
            throw new IOException("You can't access the network from the main thread. See Slack for some intel.");
        }

        return builder.toString();
    }

    public static JSONObject fetchObject(URL url) throws JSONException, IOException {
        return new JSONObject(fetch(url));
    }

    public static JSONArray fetchArray(URL url) throws JSONException, IOException {
        return new JSONArray(fetch(url));
    }
}
