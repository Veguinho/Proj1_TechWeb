package mvc.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;

import java.awt.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchPlaylistsExample {
  private static final String accessToken = ClientCredentialsExample.clientCredentials_Sync();

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  
  
  public static LinkedList<String> SearchPlaylist(String content) throws SpotifyWebApiException, IOException, JSONException {
	  SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(content).market(CountryCode.BR).limit(10).offset(0).build();
	  String json = searchPlaylistsRequest.getJson();
	  JSONObject parsed = new JSONObject(json);	
//	  System.out.println(parsed);
      String items = ((JSONObject) parsed.get("playlists")).get("items").toString();
	  String[] lista = items.split("owner");
	  LinkedList<String> links = new LinkedList<String>();
	  for (String i: lista) {
		  try {	
			  Integer index = i.indexOf("https://open.spotify.com/playlist/");
			  String playlist = i.substring(index,index + 56);
			  links.add(playlist);
		  }
		  catch (Exception e) {
 
  }
}
	  return(links);
  }}

 
 