package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Call {

    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("application_sid")
    private String applicationSid;

    @JsonProperty("call_id")
    private String callId;

    @JsonProperty("call_sid")
    private String callSid;

    @JsonProperty("call_status")
    private String callStatus;

    @JsonProperty("caller_name")
    private String callerName;

    @JsonProperty("caller_id")
    private String callerId;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("from")
    private String from;

    @JsonProperty("originating_sip_ip")
    private String originatingSipIp;

    @JsonProperty("originating_sip_trunk_name")
    private String originatingSipTrunkName;

    @JsonProperty("parent_call_sid")
    private String parent_call_sid;

    @JsonProperty("service_url")
    private String service_url;

    @JsonProperty("sip_status")
    private int sip_status;

    @JsonProperty("to")
    private String to;


}
