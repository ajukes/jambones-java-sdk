package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sip_realm")
    private String sipRealm;

    @JsonProperty("service_provider_sid")
    private String serviceProviderSid;

    @JsonProperty("is_active")
    private boolean isActive;

    @JsonProperty("device_calling_application_sid")
    private String deviceCallingApplicationSid;

    @JsonProperty("registration_hook")
    private Hook registrationHook;

}
