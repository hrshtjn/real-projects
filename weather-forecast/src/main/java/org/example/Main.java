package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    // Replace with your own API key
    private static final String API_KEY = "46b6d32d9fb4f359660f47f3d8e4ed6";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast?q=";

    public static void main(String[] args) {

        String city="Ahmedabad";
        String urlString = BASE_URL + city + "&appid="+API_KEY;
        System.out.println(urlString);

        try {
            String data = fetchWeatherData(urlString);

            if (data != null) {
                parseAndDisplayWeatherData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fetchWeatherData(String urlString) throws IOException {
        URL url = new URL(urlString);
        StringBuilder data = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Check if the request was successful
        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                data.append(scanner.nextLine());
            }
            scanner.close();
        }
        return data.toString();
    }

    public static void parseAndDisplayWeatherData(String data) {

        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        JsonArray weatherArray = jsonObject.getAsJsonArray("list");

        for (int i = 0; i < weatherArray.size(); i++) {
            JsonObject weatherObject = weatherArray.get(i).getAsJsonObject();
            JsonObject main = weatherObject.get("main").getAsJsonObject();

            double temp = main.get("temp").getAsDouble();
            double tempMin = main.get("temp_min").getAsDouble();
            double tempMax = main.get("temp_max").getAsDouble();
            double humidity = main.get("humidity").getAsDouble();
            String dateTime = weatherObject.get("dt_txt").getAsString();

            System.out.println("Date/Time: " + dateTime);
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Min Temperature: " + tempMin + "°C");
            System.out.println("Max Temperature: " + tempMax + "°C");
            System.out.println("Humidity: " + humidity + '%');
            System.out.println("-------------------------------");

        }
    }
}