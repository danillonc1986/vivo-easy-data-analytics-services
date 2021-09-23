package br.com.zup.vivo.easy.da;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.v2.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.paging.Page;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.Buckets;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class AppTest {

    private static final String CLIENT_ID = "GOOG1EVN7GI6BDBDEN42OHU6C6KIP6SZ54Z4ZIH7U2QZM5XACHQCDO6E7EJ3Y";
    private static final String CLIENT_SECRET = "q4rGtoTjO0SOnr/BlljqKbCa0KhDdUY3htLaonu1";

    private static Storage storage;

    private static String authorize(String redirectUri) {

        AuthorizationCodeRequestUrl authorizationUrl;

        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();

        web.setClientId(CLIENT_ID);
        web.setClientSecret(CLIENT_SECRET);

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();

        clientSecrets.setWeb(web);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new ApacheHttpTransport(),
                GsonFactory.getDefaultInstance(),
                clientSecrets,
                Collections.singleton(StorageScopes.DEVSTORAGE_READ_ONLY))
                .setApprovalPrompt("force")
                .setAccessType("offline")
                .build();

        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri);

        return authorizationUrl.build();
    }


    static void requestAccessToken() throws IOException {

        authorize("http://localhost");

      /*  List<String> responsetype = Arrays.asList("code");

        // Step 1: Authorize -->
                String authorizationUrl = new GoogleBrowserClientRequestUrl("690078271972-48v739uhl2aet7sfg73hn4th7d52amv6.apps.googleusercontent.com", "http://localhost",
                        Collections.singleton(StorageScopes.DEVSTORAGE_READ_ONLY))
                        .setResponseTypes(responsetype)
                        .build();

        // Point or redirect your user to the authorizationUrl.
                System.out.println("Go to the following link in your browser:");
                System.out.println(authorizationUrl);
*/
        try {
            GoogleTokenResponse response =
                    new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new GsonFactory(),
                            "690078271972-48v739uhl2aet7sfg73hn4th7d52amv6.apps.googleusercontent.com", "OByy2YCdlrjdFskgqVneLFdW",
                            "4/0AX4XfWia-9Bang9wBKwIQjZzW9u0jbphzvW8i7bMrZOOCo0uIukBibcgszpXae0wcxcfLw", "http" +
                            "://localhost")
                            .execute();

            System.out.println("Access token: " + response.getAccessToken());
        } catch (TokenResponseException e) {
            e.printStackTrace();
        }
    }

    private static Credential getCredentials(String clientId, String clientSecret) throws IOException {

        // Load client secrets.
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();

        GoogleClientSecrets.Details details = new GoogleClientSecrets.Details();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);

        clientSecrets.setInstalled(details);



        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new ApacheHttpTransport(),
                GsonFactory.getDefaultInstance(),
                clientSecrets,
                Collections.singleton(StorageScopes.DEVSTORAGE_READ_ONLY))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("/home/danilo/")))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("PC2");
    }

    public static void main(String... args) throws Exception {


        /*HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory =  GsonFactory.getDefaultInstance();
        Credential credential = CredentialsProvider.authorize(httpTransport, jsonFactory);

        com.google.api.services.storage.Storage storage = new com.google.api.services.storage.Storage.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google-ObjectsGetMetadataExample/1.0").build();
        StorageObject object = get(storage, BUCKET_NAME, OBJECT_NAME);
        System.out.println(object.getName() + " (size: " + object.getSize() + ")");*/



        Credential credential = getCredentials(
                "690078271972-48v739uhl2aet7sfg73hn4th7d52amv6.apps.googleusercontent.com",
                "OByy2YCdlrjdFskgqVneLFdW");


        Storage storage = StorageOptions.getDefaultInstance().getService();

        com.google.api.services.storage.Storage macPhotos = new com.google.api.services.storage.Storage.Builder( new ApacheHttpTransport(),GsonFactory.getDefaultInstance(),credential)
                .setApplicationName("macphotos")
                .build();

        com.google.api.services.storage.Storage.Buckets buckets = macPhotos.buckets();
        Buckets buckets1 = buckets.list("macphotos").execute();


        for( com.google.api.services.storage.model.Bucket bucket : buckets1.getItems()){
            System.out.println(bucket.getName());
        }

        System.out.println(credential);

    }


}
