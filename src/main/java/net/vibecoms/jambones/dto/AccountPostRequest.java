package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class AccountPostRequest {

    @NotNull
    @NotBlank
    @JsonProperty("service_provider_sid")
    private String serviceProviderSid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sip_realm")
    private String sipRealm;

    @JsonProperty("registration_hook")
    private Hook registrationHook;

    @JsonProperty("device_calling_hook")
    private Hook deviceCallingHook;

    @JsonProperty("error_hook")
    private Hook errorHook;

    private AccountPostRequest() {
    }

    protected AccountPostRequest(String serviceProviderSid, String name, String sipRealm, Hook registrationHook, Hook deviceCallingHook, Hook errorHook) {
        this.serviceProviderSid = serviceProviderSid;
        this.name = name;
        this.sipRealm = sipRealm;
        this.registrationHook = registrationHook;
        this.deviceCallingHook = deviceCallingHook;
        this.errorHook = errorHook;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String serviceProviderSid;
        private String name;
        private String sipRealm;
        private Hook errorHook;
        private Hook deviceCallingHook;
        private Hook registrationHook;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder serviceProviderSid(String serviceProviderSid) {
            this.serviceProviderSid = serviceProviderSid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder sipRealm(String realm) {
            this.sipRealm = realm;
            return this;
        }

        public Builder registrationHook(String url, String method) {
            this.registrationHook = new Hook(url, method);
            return this;
        }

        public Builder registrationHook(String url, String method, String username, String password) {
            this.registrationHook = new Hook(url, method, username, password);
            return this;
        }

        public Builder deviceCallingHook(String url, String method) {
            this.deviceCallingHook = new Hook(url, method);
            return this;
        }

        public Builder deviceCallingHook(String url, String method, String username, String password) {
            this.deviceCallingHook = new Hook(url, method, username, password);
            return this;
        }

        public Builder errorHook(String url, String method) {
            this.errorHook = new Hook(url, method);
            return this;
        }

        public Builder errorHook(String url, String method, String username, String password) {
            this.errorHook = new Hook(url, method, username, password);
            return this;
        }

        public AccountPostRequest build() {
            return new AccountPostRequest(serviceProviderSid, name, sipRealm, registrationHook, deviceCallingHook, errorHook);
        }
    }

}
