package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallPutRequest {

    @NotEmpty
    @JsonIgnore
    private String accountSid;

    @NotEmpty
    @JsonIgnore
    private String callSid;

    @JsonProperty("call_hook")
    private Hook callHook;

    @JsonProperty("call_status_hook")
    private Hook callStatusHook;

    @JsonProperty("whisper")
    private Hook whisper;

    @JsonProperty("call_status")
    private String callStatus;

    @JsonProperty("listen_status")
    private String listenStatus;

    private CallPutRequest() {
    }

    public CallPutRequest(String accountSid, String callSid, Hook callHook, Hook callStatusHook, Hook whisper, String callStatus, String listenStatus) {
        this.accountSid = accountSid;
        this.callSid = callSid;
        this.callHook = callHook;
        this.callStatusHook = callStatusHook;
        this.whisper = whisper;
        this.callStatus = callStatus;
        this.listenStatus = listenStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String accountSid;
        private String callSid;
        private Hook callHook;
        private Hook callStatusHook;
        private Hook whisper;
        private String callStatus;
        private String listenStatus;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder accountSid(String accountSid) {
            this.accountSid = accountSid;
            return this;
        }

        public Builder callSid(String callSid) {
            this.callSid = callSid;
            return this;
        }

        public Builder callStatus(String callStatus) {
            this.callStatus = callStatus;
            this.listenStatus = null;
            this.whisper = null;
            return this;
        }

        public Builder listenStatus(String listenStatus) {
            this.listenStatus = listenStatus;
            this.callStatus = null;
            this.whisper = null;
            return this;
        }

        public Builder callHook(String url, String method) {
            this.callHook = new Hook(url, method);
            return this;
        }

        public Builder callHook(String url, String method, String username, String password) {
            this.callHook = new Hook(url, method, username, password);
            return this;
        }

        public Builder callStatusHook(String url, String method) {
            this.callStatusHook = new Hook(url, method);
            return this;
        }

        public Builder callStatusHook(String url, String method, String username, String password) {
            this.callStatusHook = new Hook(url, method, username, password);
            return this;
        }


        public Builder whisper(String url, String method) {
            this.whisper = new Hook(url, method);
            this.callStatus = null;
            this.listenStatus = null;
            return this;
        }

        public Builder whisper(String url, String method, String username, String password) {
            this.whisper = new Hook(url, method, username, password);
            this.callStatus = null;
            this.listenStatus = null;
            return this;
        }

        public CallPutRequest build() {
            return new CallPutRequest(accountSid, callSid, callHook, callStatusHook, whisper, callStatus, listenStatus);
        }
    }
}
