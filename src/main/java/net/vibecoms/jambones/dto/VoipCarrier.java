package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoipCarrier {

    @JsonProperty("voip_carrier_sid")
    private String voipCarrierSid;

    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("application_sid")
    private String applicationSid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    private VoipCarrier(){}
}
