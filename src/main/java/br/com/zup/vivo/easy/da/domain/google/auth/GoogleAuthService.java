package br.com.zup.vivo.easy.da.domain.google.auth;

import org.springframework.stereotype.Service;

@Service
public class GoogleAuthService {

   /* private static final String CLIENT_ID = "690078271972-7cg7djrjc2be2q3nvl1iu7l9mgfiopl3.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "PhOjATn76uOF6WVA2MOVz1xl";


    public Credential getCredentials(String clientId, String clientSecret) throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();

        GoogleClientSecrets.Details details = new GoogleClientSecrets.Details();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);

        clientSecrets.setInstalled(details);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new ApacheHttpTransport(), GsonFactory.getDefaultInstance(), clientSecrets,
                Collections.singleton(StorageScopes.DEVSTORAGE_READ_ONLY))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("/home/danilo/")))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public String authorize(String redirectUri, String clientId, String clientSecret) {

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
*/
}
