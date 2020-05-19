package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.dto.LoginRequest;
import net.vibecoms.jambones.exceptions.JambonesLoginError;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URL;

public class LoginService {

    private static LoginService INSTANCE;
    private final JambonesRestClient<Void, LoginRequest, Void> restClient;

    public LoginService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.LOGIN;
        this.restClient = new JambonesRestClient<>(Void.class, LoginRequest.class, Void.class, httpClient, adminKey, path, objectMapper);
    }

    public static LoginService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new LoginService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }


    public Created post(LoginRequest loginRequest) {
        try {
            return restClient.post(loginRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JambonesLoginError("Could not login");
        }
    }
}
