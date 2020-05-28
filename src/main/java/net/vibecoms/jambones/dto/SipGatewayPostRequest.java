package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SipGatewayPostRequest {

    private String voipCarrierSid;
    private String ipv4;
    private Integer port;
    private Boolean isActive;
    private Boolean inbound;
    private Boolean outbound;

    @JsonProperty("voip_carrier_sid")
    public String getVoipCarrierSid() {
        return voipCarrierSid;
    }

    @JsonProperty("ipv4")
    public String getIpv4() {
        return ipv4;
    }

    @JsonProperty("port")
    public int getPort() {
        return port;
    }

    @JsonProperty("is_active")
    public boolean isActive() {
        return isActive;
    }

    @JsonProperty("inbound")
    public boolean isInbound() {
        return inbound;
    }

    @JsonProperty("outbound")
    public boolean isOutbound() {
        return outbound;
    }

    private SipGatewayPostRequest() {
    }


}
