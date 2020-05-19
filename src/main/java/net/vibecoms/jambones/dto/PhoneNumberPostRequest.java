package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumberPostRequest {

    @JsonProperty("voip_carrier_sid")
    private String voipCarrierSid;

    @JsonProperty("number")
    private String number;

    private PhoneNumberPostRequest() {
    }
}
