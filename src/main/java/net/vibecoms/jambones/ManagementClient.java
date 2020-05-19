package net.vibecoms.jambones;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import net.vibecoms.jambones.auth.JambonesAdminKey;
import net.vibecoms.jambones.dto.LoginRequest;
import net.vibecoms.jambones.exceptions.JambonesConstraintViolation;
import net.vibecoms.jambones.services.*;
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
public final class ManagementClient {

    @NotNull
    private final CloseableHttpClient httpClient;
    @NotEmpty
    private JambonesAdminKey adminKey;
    @NotNull
    private final URL endpoint;
    @NotEmpty
    private final String version;
    @NotNull
    private final ObjectMapper objectMapper;


    protected ManagementClient(CloseableHttpClient httpClient, JambonesAdminKey adminKey, URL endpoint, String version) {
        this.httpClient = httpClient;
        this.adminKey = adminKey;
        this.endpoint = endpoint;
        this.version = version;
        this.objectMapper = new ObjectMapper();
    }

    public AccountService account() {
        return AccountService.getInstance(httpClient, adminKey, endpoint, version, objectMapper);
    }

    public APIKeyService apiKey() {
        return APIKeyService.getInstance(httpClient, adminKey, endpoint, version, objectMapper);
    }

    public ApplicationService application() {
        return ApplicationService.getInstance(httpClient, adminKey, endpoint, version, objectMapper);
    }

    public PhoneNumberService phoneNumber() {
        return PhoneNumberService.getInstance(httpClient, adminKey, endpoint, version, objectMapper);
    }

    public ServiceProviderService serviceProvider() {
        return ServiceProviderService.getInstance(httpClient, adminKey, endpoint, version, objectMapper);
    }

    public SipGatewayService sipGateway() {
        return SipGatewayService.getInstance(httpClient, adminKey, endpoint, version, objectMapper);
    }

    public VoipCarrierService voipCarrier() {
        return VoipCarrierService.getInstance(httpClient, adminKey, endpoint, version, objectMapper);
    }

    public ManagementClient login(String username, String password) {
        String sid = LoginService.getInstance(httpClient, adminKey, endpoint, version, objectMapper)
                .post(new LoginRequest(username, password))
                .getToken();
        this.adminKey = new JambonesAdminKey(sid);
        return this;
    }

    public static Builder standard() {
        return new Builder("v1", HttpClients.createDefault());
    }

    public static Builder custom() {
        return new Builder();
    }

    public static class Builder {

        private CloseableHttpClient httpClient;
        private JambonesAdminKey adminKey;
        private URL endpoint;
        private String version;

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


        public Builder adminKey(String key) {
            this.adminKey = new JambonesAdminKey(key);
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

        public ManagementClient build() {
            ManagementClient managementClient = new ManagementClient(httpClient, adminKey, endpoint, version);
            Set<ConstraintViolation<ManagementClient>> constraintViolations = validator.validate(managementClient);
            if (constraintViolations.size() > 0) {
                throw new JambonesConstraintViolation(constraintViolations);
            }
            return managementClient;
        }


    }
}
