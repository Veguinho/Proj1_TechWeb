package mvc.model;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.data.json.recentlyplayedgames.GetRecentlyPlayedGames;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient.SteamWebApiClientBuilder;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

//https://github.com/lpradel/steam-web-api-java

public class SteamWebApiDemo {

    public static String[] getRecentlyPlayed(String SteamID) throws SteamApiException, MalformedURLException, JSONException, IOException {
    	String a = "Auth";
    	String b = SteamID;
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(a).build();    

        String recentPlayed = "http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key="+a+"&steamid="+b+"&format=json";
        JSONObject json = new JSONObject(IOUtils.toString(new URL(recentPlayed), Charset.forName("UTF-8")));
        String teste = ((JSONObject) json.get("response")).get("games").toString();
        
        String[] parsed1 = teste.split("},");
//        System.out.println(parsed1.length);

        String[] games = new String[parsed1.length ];
//        System.out.println(Arrays.toString(parsed1));
        for (int j = 0; j < parsed1.length; j++) {
			Integer gameName = parsed1[j].indexOf("name");
			Integer refer = parsed1[j].indexOf("img_logo");
			
			String game = parsed1[j].substring(gameName+7, refer -3);
			games[j] = game;

		}
        return games;
        
        }
    }

