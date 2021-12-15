package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.dto.SipGateway;
import net.vibecoms.jambones.dto.SipGatewayPostRequest;
import net.vibecoms.jambones.dto.SipGatewayPutRequest;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class SipGatewayService {

    private static SipGatewayService INSTANCE;
    private final JambonesRestClient<SipGateway, SipGatewayPostRequest, SipGatewayPutRequest> restClient;

    public SipGatewayService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.SIP_GATEWAY;
        this.restClient = new JambonesRestClient<>(SipGateway.class, SipGatewayPostRequest.class, SipGatewayPutRequest.class, httpClient, adminKey, path, objectMapper);
    }

    public static SipGatewayService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new SipGatewayService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }

    public List<SipGateway> list(String voipCarrierSid) {
        try {
            Map<String, String> params = new HashMap<>();
            if (voipCarrierSid != null) {
                params.put("voip_carrier_sid", voipCarrierSid);
            }
            return restClient.get(params);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<SipGateway> get(String sipGatewaySid) {
        try {
            return Optional.ofNullable(restClient.get(sipGatewaySid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Created post(SipGatewayPostRequest sipGatewayPostRequest) {
        try {
            return restClient.post(sipGatewayPostRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create SipGateway");
        }
    }

    public void put(SipGatewayPutRequest sipGatewayPutRequest) {
        try {
            restClient.put(sipGatewayPutRequest, sipGatewayPutRequest.getSipGatewaySid());
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not update SipGateway");
        }
    }

    public void delete(String sipGatewaySid) {
        try {
            restClient.delete(sipGatewaySid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete SipGateway");
        }
    }

}
