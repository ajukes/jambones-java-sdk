package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumber {


    @JsonProperty("phone_number_sid")
    private String phoneNumberSid;

    @JsonProperty("voip_carrier_sid")
    private String voipCarrierSid;

    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("application_sid")
    private String applicationSid;

    @JsonProperty("number")
    private String number;

    private PhoneNumber() {
    }
}
