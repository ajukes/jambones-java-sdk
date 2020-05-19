package net.vibecoms.jambones;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import net.vibecoms.jambones.auth.JambonesAPIKey;
import net.vibecoms.jambones.exceptions.JambonesConstraintViolation;
import net.vibecoms.jambones.services.CallService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

@ToString
public final class CallControlClient {

    @NotNull
    private final CloseableHttpClient httpClient;
    @NotEmpty
    private final JambonesAPIKey apiKey;
    @NotEmpty
    private final String accountSid;
    @NotNull
    private final URL endpoint;
    @NotEmpty
    private final String version;
    @NotNull
    private final ObjectMapper objectMapper;
    @NotNull
    private final CallService callService;

    protected CallControlClient(CloseableHttpClient httpClient, JambonesAPIKey apiKey, URL endpoint, String version, String accountSid) {
        this.httpClient = httpClient;
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.version = version;
        this.accountSid = accountSid;
        this.objectMapper = new ObjectMapper();
        this.callService = new CallService(httpClient, apiKey, endpoint, version, objectMapper, accountSid);
    }


    public CallService calls() {
        return callService;
    }

    public static Builder standard() {
        return new Builder("v1", HttpClients.createDefault());
    }

    public static Builder custom() {
        return new Builder();
    }

    public static class Builder {

        private CloseableHttpClient httpClient;
        private JambonesAPIKey apiKey;
        private URL endpoint;
        private String version;
        private String accountSid;

        private final Validator validator;

        private Builder() {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        }

        private Builder(String version, CloseableHttpClient httpClient) {
            this.httpClient = httpClient;
            this.version = version;

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        }


        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder httpClient(CloseableHttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }


        public Builder apiKey(String key) {
            this.apiKey = new JambonesAPIKey(key);
            return this;
        }

        public Builder endpoint(String endpoint) {
            try {
                this.endpoint = new URL(endpoint);
                return this;
            } catch (MalformedURLException e) {
                throw new AssertionError(endpoint + " is not a valid URL");
            }
        }

        public Builder accountSid(String accountSid) {
            this.accountSid = accountSid;
            return this;
        }

        public CallControlClient build() {
            CallControlClient callControlClient = new CallControlClient(httpClient, apiKey, endpoint, version, accountSid);
            Set<ConstraintViolation<CallControlClient>> constraintViolations = validator.validate(callControlClient);
            if (constraintViolations.size() > 0) {
                throw new JambonesConstraintViolation(constraintViolations);
            }
            return callControlClient;
        }


    }
}
