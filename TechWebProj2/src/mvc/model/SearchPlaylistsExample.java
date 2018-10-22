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

import java.io.IOException;
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
  
  
  public static String teste(String content) throws SpotifyWebApiException, IOException, JSONException {
	  SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(content).market(CountryCode.BR).limit(10).offset(0).build();
	  String json = searchPlaylistsRequest.getJson();
//	  System.out.print(json);
	  JSONObject parsed = new JSONObject(json);	  
	  Integer index = parsed.get("playlists").toString().indexOf("https://open.spotify.com/playlist/");
	  String playlist = parsed.get("playlists").toString().substring(index,index + 56);
	  return playlist;
	
 
  }
  
  



}

 
 