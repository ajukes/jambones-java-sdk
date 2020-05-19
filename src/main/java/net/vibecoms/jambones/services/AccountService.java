package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.Account;
import net.vibecoms.jambones.dto.AccountPostRequest;
import net.vibecoms.jambones.dto.AccountPutRequest;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.utils.URLConstants;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private static AccountService INSTANCE;
    private final JambonesRestClient<Account, AccountPostRequest, AccountPutRequest> restClient;

    public AccountService(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        String path = url.toString() + "/" + version + "/" + URLConstants.ACCOUNT;
        this.restClient = new JambonesRestClient<>(Account.class, AccountPostRequest.class, AccountPutRequest.class, httpClient, adminKey, path, objectMapper);
    }

    public static AccountService getInstance(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL url, String version, ObjectMapper objectMapper) {
        if (INSTANCE == null) {
            INSTANCE = new AccountService(httpClient, adminKey, url, version, objectMapper);
        }
        return INSTANCE;
    }

    public List<Account> list() {
        try {
            return restClient.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Account> get(String accountSid) {
        try {
            return Optional.ofNullable(restClient.get(accountSid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Created post(AccountPostRequest postRequest) {
        try {
            return restClient.post(postRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not create Account");
        }
    }

    public void put(AccountPutRequest accountPutRequest) {
        try {
            restClient.put(accountPutRequest, accountPutRequest.getAccountSid());
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not update Account");
        }
    }

    public void delete(String accountSid) {
        try {
            restClient.delete(accountSid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Could not delete Account");
        }
    }

}
