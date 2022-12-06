import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WikipediaInfo {
    public static String info (String moviename){
        String encoding = "UTF-8";
        String result = "";
        String wikipediaURL;
        try {
            if(moviename.equals("Black Adam")){
                wikipediaURL = "https://en.wikipedia.org/wiki/Black_Adam_(film)";
            }
            else if(moviename.equals("Smile")){
                wikipediaURL = "https://en.wikipedia.org/wiki/Smile_(2022_film)";
            }
            else{
                wikipediaURL = "https://en.wikipedia.org/wiki/Thor:_Love_and_Thunder";
            }
				
				//Use Wikipedia API to get JSON File
			String wikipediaApiJSON = "https://www.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="
                + URLEncoder.encode(wikipediaURL.substring(wikipediaURL.lastIndexOf("/") + 1, wikipediaURL.length()), encoding);
            
            HttpURLConnection httpcon = (HttpURLConnection) new URL(wikipediaApiJSON).openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));

            String responseSB = in.lines().collect(Collectors.joining());
		    in.close();

            result = responseSB.split("extract\":\"")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
