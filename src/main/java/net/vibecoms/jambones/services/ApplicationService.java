package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.Application;
import net.vibecoms.jambones.dto.ApplicationPostRequest;
import net.vibecoms.jambones.dto.ApplicationPutRequest;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ApplicationService {

    private static ApplicationService INSTANCE;
    private final JambonesRestClient<Application, ApplicationPostRequest, ApplicationPutRequest> restClient;

    public ApplicationService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.APPLICATION;
        this.restClient = new JambonesRestClient<>(Application.class, ApplicationPostRequest.class, ApplicationPutRequest.class, httpClient, adminKey, path, objectMapper);
    }

    public static ApplicationService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }

    public List<Application> list() {
        try {
            return restClient.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Application> get(String applicationSid) {
        try {
            return Optional.ofNullable(restClient.get(applicationSid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Created post(ApplicationPostRequest postRequest) {
        try {
            return restClient.post(postRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create Application");
        }
    }

    public void put(ApplicationPutRequest applicationPutRequest) {
        try {
            restClient.put(applicationPutRequest, applicationPutRequest.getApplicationSid());
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not update Application");
        }
    }

    public void delete(String applicationSid) {
        try {
            restClient.delete(applicationSid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete Application");
        }
    }

}
