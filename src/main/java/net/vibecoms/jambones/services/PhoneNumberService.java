package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.dto.PhoneNumber;
import net.vibecoms.jambones.dto.PhoneNumberPostRequest;
import net.vibecoms.jambones.dto.PhoneNumberPutRequest;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PhoneNumberService {

    private static PhoneNumberService INSTANCE;
    private final JambonesRestClient<PhoneNumber, PhoneNumberPostRequest, PhoneNumberPutRequest> restClient;

    public PhoneNumberService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.PHONE_NUMBER;
        this.restClient = new JambonesRestClient<>(PhoneNumber.class, PhoneNumberPostRequest.class, PhoneNumberPutRequest.class, httpClient, adminKey, path, objectMapper);
    }

    public static PhoneNumberService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new PhoneNumberService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }

    public List<PhoneNumber> list() {
        try {
            return restClient.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<PhoneNumber> get(String phoneNumberSid) {
        try {
            return Optional.ofNullable(restClient.get(phoneNumberSid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Created post(PhoneNumberPostRequest phoneNumberPostRequest) {
        try {
            return restClient.post(phoneNumberPostRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create PhoneNumber");
        }
    }

    public void put(PhoneNumberPutRequest phoneNumberPutRequest) {
        try {
            restClient.put(phoneNumberPutRequest, phoneNumberPutRequest.getPhoneNumberSid());
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not update PhoneNumber");
        }
    }

    public void delete(String phoneNumberSid) {
        try {
            restClient.delete(phoneNumberSid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete PhoneNumber");
        }
    }

}
