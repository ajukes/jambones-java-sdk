package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.dto.ServiceProvider;
import net.vibecoms.jambones.dto.ServiceProviderPostRequest;
import net.vibecoms.jambones.dto.ServiceProviderPutRequest;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ServiceProviderService {

    private static ServiceProviderService INSTANCE;
    private final JambonesRestClient<ServiceProvider, ServiceProviderPostRequest, ServiceProviderPutRequest> restClient;

    public ServiceProviderService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.SERVICE_PROVIDER;
        this.restClient = new JambonesRestClient<>(ServiceProvider.class, ServiceProviderPostRequest.class, ServiceProviderPutRequest.class, httpClient, adminKey, path, objectMapper);
    }

    public static ServiceProviderService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new ServiceProviderService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }

    public List<ServiceProvider> list() {
        try {
            return restClient.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<ServiceProvider> get(String serviceProviderSid) {
        try {
            return Optional.ofNullable(restClient.get(serviceProviderSid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Created post(ServiceProviderPostRequest serviceProviderPostRequest) {
        try {
            return restClient.post(serviceProviderPostRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create ServiceProvider");
        }
    }

    public void put(ServiceProviderPutRequest serviceProviderPutRequest) {
        try {
            restClient.put(serviceProviderPutRequest, serviceProviderPutRequest.getServiceProviderSid());
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not update ServiceProvider");
        }
    }

    public void delete(String serviceProviderSid) {
        try {
            restClient.delete(serviceProviderSid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete ServiceProvider");
        }
    }

}
