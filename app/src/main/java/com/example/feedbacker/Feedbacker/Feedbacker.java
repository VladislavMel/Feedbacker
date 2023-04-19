package com.example.feedbacker.Feedbacker;

import com.example.feedbacker.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Feedbacker {

    public static ArrayList<Feedback> getReviews(String organization) throws IOException {
        // Получение информации о заведении в json формате
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().
                url("https://search-maps.yandex.ru/v1/?text=" + organization + ",Волгоград&type=biz&lang=ru_RU&results=1&apikey=" + Constants.YANDEX_ORGANIZATIONS_API).
                build();
        Response response = client.newCall(request).execute();


        // Получение id органзации
        assert response.body() != null;
        JsonObject object = JsonParser.parseString(response.body().string()).getAsJsonObject();
        JsonArray features = (JsonArray) object.get("features");
        JsonObject item = (JsonObject) features.get(0);
        JsonObject properties = (JsonObject) item.get("properties");
        JsonObject companyMetaData = (JsonObject) properties.get("CompanyMetaData");
        String id = String.valueOf(companyMetaData.get("id")).replace("\"", "");

        Document doc = Jsoup.connect(getOrganizationPage(id)).get();

        // Создание массива с отзывами
        ArrayList<Feedback> feedbacks = new ArrayList<>();

        Elements divs = doc.select("div.business-review-view__info");
        for (Element div : divs) {
            feedbacks.add(new Feedback(div.select("div.business-review-view__author span").text(),
                    div.select("span.business-review-view__body-text").text()));
        }

        return feedbacks;
    }

    // Метод для формирования ссылки на страницу организации по переданному id
    private static String getOrganizationPage (String id) {
        return "https://yandex.ru/maps/org/" + id + "/reviews";
    }
}
