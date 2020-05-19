package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceProviderPutRequest {

    @JsonIgnore
    private String serviceProviderSid;

    @NotNull
    @NotBlank
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("root_domain")
    private String rootDomain;

    @JsonProperty("registration_hook")
    private String registrationHook;

    @JsonProperty("hook_basic_auth_user")
    private String hookBasicAuthUser;

    @JsonProperty("hook_basic_auth_password")
    private String hookBasicAuthPassword;

    private ServiceProviderPutRequest() {
    }
}
