package grapp.grapp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

public class imgUrlScraper {
    private static String imageUrl = "https://i.imgur.com/";
    private static String getImgUrl = "https://api.imgur.com/3/image/";
    private static String postImgUrl = "https://api.imgur.com/3/upload";
    private static String exceptionImgId = "BUxBHLB.png";
    private static String clientId = "492e7d659e0645a"; //TODO add to env variables

    public static String searchById(String id){
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Client-ID "+clientId);

            JSONObject json = Unirest.get(getImgUrl+id).headers(headers).asJson().getBody().getObject();
            if(json.getBoolean("success")) return json.getJSONObject("data").getString("link");
        } 
        catch (UnirestException e) {
            return imageUrl+exceptionImgId;
        }
        return imageUrl+exceptionImgId;
    }

    public static String uploadImg(File img){
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Client-ID "+clientId);
            Map<String, Object> fields = new HashMap<>();
            fields.put("image", img);
            fields.put("type", "file");
            JSONObject json = Unirest.post(postImgUrl).headers(headers).fields(fields).asJson().getBody().getObject();
            System.out.println(json);
            if(json.getBoolean("success")) return json.getJSONObject("data").getString("link");
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            return "fail";
        }
        return "fail";
    }

}

