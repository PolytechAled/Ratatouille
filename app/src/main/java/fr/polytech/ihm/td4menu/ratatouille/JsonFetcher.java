package fr.polytech.ihm.td4menu.ratatouille;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class JsonFetcher {

    public static JSONObject fetch(URL url) throws JSONException, IOException {
        StringBuilder builder = new StringBuilder();
        URLConnection connection = url.openConnection();

        connection.setRequestProperty("User-Agent", "Ratatouille Android App");
        connection.setRequestProperty("Accept", "application/json");

        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine()).append('\n');
            }
        }

        return new JSONObject(builder.toString());
    }
}
