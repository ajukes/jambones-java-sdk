package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAPIKey;
import net.vibecoms.jambones.dto.Call;
import net.vibecoms.jambones.dto.CallPostRequest;
import net.vibecoms.jambones.dto.CallPutRequest;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CallService {

    private final JambonesRestClient<Call, CallPostRequest, CallPutRequest> restClient;
    private final String accountSid;

    public CallService(CloseableHttpClient httpClient, JambonesAPIKey adminKey, URL url, String version, ObjectMapper objectMapper, String accountSid) {
        String path = url.toString() + "/" + version + "/" + URLConstants.ACCOUNT + "/" + accountSid + "/" + URLConstants.CALL;
        this.restClient = new JambonesRestClient<>(Call.class, CallPostRequest.class, CallPutRequest.class, httpClient, adminKey, path, objectMapper);
        this.accountSid = accountSid;
    }

    public List<Call> list() {
        try {
            return restClient.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Call> get(String callSid) {
        try {
            return Optional.ofNullable(restClient.get(callSid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Created post(CallPostRequest postRequest) {
        try {
            return restClient.post(postRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create Call");
        }
    }

    public void put(CallPutRequest callPutRequest) {
        try {
            restClient.put(callPutRequest, callPutRequest.getAccountSid() + "/" + callPutRequest.getCallSid());
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not update Call");
        }
    }

    public void delete(String callSid) {
        try {
            restClient.delete(callSid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete Call");
        }
    }

}
