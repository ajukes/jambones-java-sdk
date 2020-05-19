package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumberPutRequest {


    @JsonIgnore
    private String phoneNumberSid;

    @JsonProperty("voip_carrier_sid")
    private String voipCarrierSid;

    @JsonProperty("number")
    private String number;

    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("application_sid")
    private String applicationSid;

    private PhoneNumberPutRequest() {
    }

}
