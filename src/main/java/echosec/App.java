package echosec;

import echosec.RNNSentiment;
import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLOutput;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

public class App {
    static RNNSentiment sentiment = new RNNSentiment();
    public static void main(String[] args) {

        get("/", (request, response) -> {
            return "I love cheese!";
        });

        post("/api/v0/sentiment", (request, response) -> {
            String body = request.body();
            JsonObject jsonBody = new JsonParser().parse(body).getAsJsonObject();
            return sentiment.compute(jsonBody.get("text").toString());
        });

    }

}
