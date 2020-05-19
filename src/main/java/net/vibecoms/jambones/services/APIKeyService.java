package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.*;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;


public class APIKeyService {

    private static APIKeyService INSTANCE;
    private final JambonesRestClient<APIKey, APIKeyPostRequest, APIKeyPutRequest> restClient;

    public APIKeyService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.API_KEY;
        this.restClient = new JambonesRestClient<>(APIKey.class, APIKeyPostRequest.class, APIKeyPutRequest.class, httpClient, adminKey, path, objectMapper);
    }

    public static APIKeyService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new APIKeyService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }

    public Created post(String accountSid, String serviceProviderSid) {
        try {

            APIKeyPostRequest postRequest = APIKeyPostRequest.builder()
                    .serviceProviderSid(serviceProviderSid)
                    .accountSid(accountSid)
                    .build();

            return restClient.post(postRequest);

        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create APIKey");
        }
    }

    public Created post(APIKeyPostRequest apiKeyPostRequest) {
        try {
            return restClient.post(apiKeyPostRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create APIKey");
        }
    }

    public void delete(String aPIKeySid) {
        try {
            restClient.delete(aPIKeySid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete APIKey");
        }
    }

}
