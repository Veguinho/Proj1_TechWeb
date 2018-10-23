package mvc.model;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//Codigo pego de https://github.com/thelinmichael/spotify-web-api-java/blob/master/examples/authorization/client_credentials/ClientCredentialsExample.java

public class ClientCredentialsExample {
  private static final String clientId = "ID";
  private static final String clientSecret = "Secret";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setClientId(clientId)
          .setClientSecret(clientSecret)
          .build();
  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
          .build();

  public static String clientCredentials_Sync() {
    try {
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
      

//      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
      return(spotifyApi.getAccessToken());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
	return null;
  }

  public static void clientCredentials_Async() {
    try {
      final Future<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();

      // ...

      final ClientCredentials clientCredentials = clientCredentialsFuture.get();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    } catch (InterruptedException | ExecutionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    }
  }
}