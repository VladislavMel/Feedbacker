package com.example.feedbacker.PizzaPlanet;

import com.example.feedbacker.PizzaPlanet.Organization;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OrganizationParser {
    public ArrayList<Organization> getOrganizations() throws IOException {
        // Запрос
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().
                url("https://search-maps.yandex.ru/v1/?text=https://search-maps.yandex.ru/v1/?text=пицца,кафе,Волгоград&type=biz&ll=48.68,44.48&spn=0.552069,0.400552&lang=ru_RU&results=10&apikey=f07e4006-27b1-4c34-ab91-37e3314ad93d,Волгоград&type=biz&lang=ru_RU&results=1&apikey=f07e4006-27b1-4c34-ab91-37e3314ad93d").
                build();
        Response response = client.newCall(request).execute();

        ArrayList<Organization> organizations = new ArrayList<>();

        // Парсинг address, id, name
        assert response.body() != null;
        JsonObject object = JsonParser.parseString(response.body().string()).getAsJsonObject();
        JsonArray features = (JsonArray) object.get("features");
        for (JsonElement item : features) {
            JsonObject properties = (JsonObject) item.getAsJsonObject().get("properties");
            JsonObject companyMetaData = (JsonObject) properties.get("CompanyMetaData");
            String address = String.valueOf(companyMetaData.get("address")).replace("\"", "");
            String name = String.valueOf(companyMetaData.get("name")).replace("\"", "");
            String id = String.valueOf(companyMetaData.get("id")).replace("\"", "");
            organizations.add(new Organization(address, name, id));
        }

        return organizations;
    }
}
