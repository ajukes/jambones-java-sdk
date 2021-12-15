package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.dto.VoipCarrier;
import net.vibecoms.jambones.dto.VoipCarrierPostRequest;
import net.vibecoms.jambones.dto.VoipCarrierPutRequest;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class VoipCarrierService {

    private static VoipCarrierService INSTANCE;
    private final JambonesRestClient<VoipCarrier, VoipCarrierPostRequest, VoipCarrierPutRequest> restClient;

    public VoipCarrierService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.VOIP_CARRIER;
        this.restClient = new JambonesRestClient<>(VoipCarrier.class, VoipCarrierPostRequest.class, VoipCarrierPutRequest.class, httpClient, adminKey, path, objectMapper);
    }

    public static VoipCarrierService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new VoipCarrierService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }

    public List<VoipCarrier> list() {
        try {
            Map<String, String> params = new HashMap<>();
            return restClient.get(params);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<VoipCarrier> get(String voipCarrierSid) {
        try {
            return Optional.ofNullable(restClient.get(voipCarrierSid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Created post(VoipCarrierPostRequest voipCarrierPostRequest) {
        try {
            return restClient.post(voipCarrierPostRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create VoipCarrier");
        }
    }

    public void put(VoipCarrierPutRequest voipCarrierPutRequest) {
        try {
            restClient.put(voipCarrierPutRequest, voipCarrierPutRequest.getVoipCarrierSid());
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not update VoipCarrier");
        }
    }

    public void delete(String voipCarrierSid) {
        try {
            restClient.delete(voipCarrierSid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete VoipCarrier");
        }
    }

}
