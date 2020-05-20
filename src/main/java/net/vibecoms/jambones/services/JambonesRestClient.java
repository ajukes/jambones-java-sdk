package net.vibecoms.jambones.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.auth.JambonesKeyProvider;
import net.vibecoms.jambones.dto.Created;
import net.vibecoms.jambones.exceptions.JambonesConstraintViolation;
import net.vibecoms.jambones.exceptions.JambonesRestException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JambonesRestClient<T, U, V> {

    public static final String APPLICATION_JSON = "application/json";

    private final Class<T> t;
    private final Class<U> u;
    private final Class<V> v;

    private final CloseableHttpClient httpClient;
    private final JambonesKeyProvider adminKey;
    private final ObjectMapper objectMapper;
    private final String url;

    private final Validator validator;

    public JambonesRestClient(
            Class<T> t, Class<U> u, Class<V> v,
            CloseableHttpClient httpClient, JambonesKeyProvider adminKey, String url, ObjectMapper objectMapper) {

        this.t = t;
        this.u = u;
        this.v = v;
        this.httpClient = httpClient;
        this.adminKey = adminKey;
        this.objectMapper = objectMapper;
        this.url = url;

        this.validator =  Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

    }

    public List<T> get() throws IOException {

        System.out.println(url);

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
        if(adminKey != null)
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, getBearer());

        return httpClient.execute(httpGet, response -> {
            if (isValidResponse(response)) {
                HttpEntity entity = response.getEntity();
                return entity != null ? objectMapper.readValue(EntityUtils.toString(entity), objectMapper.getTypeFactory().constructCollectionType(List.class, t)) : Collections.emptyList();
            } else {
                throw new ClientProtocolException(errorMsg(response));
            }
        });
    }

    public T get(String id) throws IOException {
        HttpGet httpGet = new HttpGet(url + "/" + id);
        httpGet.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
        if(adminKey != null)
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, getBearer());


        return httpClient.execute(httpGet, response -> {
            if (isValidResponse(response)) {
                HttpEntity entity = response.getEntity();
                return entity != null ? objectMapper.readValue(EntityUtils.toString(entity), t) : null;
            } else {
                throw new ClientProtocolException(errorMsg(response));
            }
        });
    }

    public Created post(U payload) throws IOException {

        validatePost(payload);

        String json = objectMapper.writeValueAsString(payload);

        HttpEntity postEntity = new StringEntity(json);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
        if(adminKey != null)
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, getBearer());
        httpPost.setEntity(postEntity);

        return httpClient.execute(httpPost, response -> {
            if (isValidResponse(response)) {
                HttpEntity entity = response.getEntity();
                return entity != null ? objectMapper.readValue(EntityUtils.toString(entity), Created.class) : null;
            } else if(response.getStatusLine().getStatusCode() == 403) {
               throw new JambonesRestException("Unauthorised");
            } else {
                throw new JambonesRestException(errorMsg(response));
            }
        });

    }


    public void put(V payload, String id) throws IOException {

        validatePut(payload);

        HttpEntity postEntity = new StringEntity(objectMapper.writeValueAsString(payload));

        HttpPut httpPut = new HttpPut(url + "/" + id);
        httpPut.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
        if(adminKey != null)
            httpPut.setHeader(HttpHeaders.AUTHORIZATION, getBearer());
        httpPut.setEntity(postEntity);

        httpClient.execute(httpPut, response -> {
            if (isValidResponse(response)) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException(errorMsg(response));
            }
        });
    }

    public void delete(String id) throws IOException {
        HttpPut httpDelete = new HttpPut(url + "/" + id);
        if(adminKey != null)
            httpDelete.setHeader(HttpHeaders.AUTHORIZATION, getBearer());
        httpClient.execute(httpDelete, response -> {
            if (isValidResponse(response)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            } else {
                throw new ClientProtocolException(errorMsg(response));
            }
        });
    }

    private String errorMsg(HttpResponse response) {
        return "Unexpected response status: " + response.getStatusLine().getStatusCode();
    }

    private boolean isValidResponse(HttpResponse response) {
        int status = response.getStatusLine().getStatusCode();
        return status >= 200 && status < 300;
    }

    private String getBearer() {
        return "Bearer " + adminKey.getKey();
    }

    private void validatePost(U payload) {
        Set<ConstraintViolation<U>> constraintViolations = validator.validate(payload);
        if (constraintViolations.size() > 0) {
            throw new JambonesConstraintViolation(constraintViolations);
        }
    }

    private void validatePut(V payload) {
        Set<ConstraintViolation<V>> constraintViolations = validator.validate(payload);
        if (constraintViolations.size() > 0) {
            throw new JambonesConstraintViolation(constraintViolations);
        }
    }

}
