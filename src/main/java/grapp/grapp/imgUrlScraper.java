package grapp.grapp;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

public class imgUrlScraper {
    private static String imageUrl = "https://i.imgur.com/";
    private static String getImgUrl = "https://api.imgur.com/3/image/";
    private static String exceptionImgId = "BUxBHLB.png";
    private static String clientId = "492e7d659e0645a"; //TODO add to env variables

    public static String searchById(String id){
        try {
            JSONObject json = Unirest.get(getImgUrl+id).header("Authorization", "Client-ID "+clientId).asJson().getBody().getObject();
            if(json.getBoolean("success")) return json.getJSONObject("data").getString("link");
        } 
        catch (UnirestException e) {
            return imageUrl+exceptionImgId;
        }
        return imageUrl+exceptionImgId;
    }
}

