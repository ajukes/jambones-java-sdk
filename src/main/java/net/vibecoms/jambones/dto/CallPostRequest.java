package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallPostRequest {

    @NotEmpty
    @JsonProperty("application_sid")
    private String applicationSid;

    @NotEmpty
    @JsonProperty("from")
    private String from;

    @NotNull
    @JsonProperty("to")
    private Target to;

    @JsonProperty("timeout")
    private int timeout;

    @JsonProperty("tag")
    private Map<String, String> tag;

    @JsonProperty("call_hook")
    private Hook callHook;

    @JsonProperty("call_status_hook")
    private Hook callStatusHook;

    private CallPostRequest() {
    }

    private CallPostRequest(String applicationSid, String from, Target to, int timeout, Map<String, String> tag, Hook callHook, Hook callStatusHook) {
        this.applicationSid = applicationSid;
        this.from = from;
        this.to = to;
        this.timeout = timeout;
        this.tag = tag;
        this.callHook = callHook;
        this.callStatusHook = callStatusHook;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String applicationSid;
        private String from;
        private Target to;
        private int timeout;
        private Map<String, String> tag;
        private Hook callHook;
        private Hook callStatusHook;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder applicationSid(String applicationSid) {
            this.applicationSid = applicationSid;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder phone(String number) {
            this.to = Target.phone(number);
            return this;
        }

        public Builder sip(String sipUri) {
            this.to = Target.sip(sipUri);
            return this;
        }

        public Builder user(String username) {
            this.to = Target.user(username);
            return this;
        }

        public Builder user(String username, String authUser, String authPassword) {
            this.to = Target.user(username, authUser, authPassword);
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder tag(Map<String, String> tag) {
            this.tag = tag;
            return this;
        }

        public Builder tag(String k, String v) {
            if (this.tag == null)
                this.tag = new HashMap<>();
            tag.put(k, v);
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

        public CallPostRequest build() {
            return new CallPostRequest(applicationSid, from, to, timeout, tag, callHook, callStatusHook);
        }
    }
}
