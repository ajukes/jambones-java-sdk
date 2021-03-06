package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SipGatewayPutRequest {

    @JsonIgnore
    private String sipGatewaySid;

    @JsonProperty("voip_carrier_sid")
    private String voipCarrierSid;

    @JsonProperty("ipv4")
    private String ipv4;

    @JsonProperty("port")
    private int port;

    @JsonProperty("is_active")
    private boolean isActive;

    @JsonProperty("inbound")
    private boolean inbound;

    @JsonProperty("outbound")
    private boolean outbound;

    private SipGatewayPutRequest(){}
}
