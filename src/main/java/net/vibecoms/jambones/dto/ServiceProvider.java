package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceProvider {

    @JsonProperty("service_provider_sid")
    private String serviceProviderSid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("root_domain")
    private String rootDomain;

    @JsonProperty("registration_hook_sid")
    private String registrationHookSid;

    @JsonProperty("ms_teams_fqdn")
    private String msTeamsFQDN;

    private ServiceProvider(){}
}
