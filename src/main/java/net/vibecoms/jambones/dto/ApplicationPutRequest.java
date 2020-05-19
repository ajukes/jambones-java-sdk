package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationPutRequest {

    @NotEmpty(message = "Must have applicationSid")
    @JsonIgnore
    private String applicationSid;

    @NotEmpty(message = "Must have name")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "Must have accountSid")
    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("speech_synthesis_vendor")
    private String speechSynthesisVendor;

    @JsonProperty("speech_synthesis_voice")
    private String speechSynthesisVoice;

    @JsonProperty("speech_recognizer_vendor")
    private String speechRecognizerVendor;

    @JsonProperty("speech_recognizer_language")
    private String speechRecognizerLanguage;

    @NotEmpty(message = "Must have callHook")
    @JsonProperty("call_hook")
    private Hook callHook;

    @NotEmpty(message = "Must have callStatusHook")
    @JsonProperty("call_status_hook")
    private Hook callStatusHook;

    private ApplicationPutRequest() {
    }

    private ApplicationPutRequest(String applicationSid, String name, String accountSid, String speechSynthesisVendor, String speechSynthesisVoice, String speechRecognizerVendor, String speechRecognizerLanguage, Hook callHook, Hook callStatusHook) {
        this.applicationSid = applicationSid;
        this.name = name;
        this.accountSid = accountSid;
        this.speechSynthesisVendor = speechSynthesisVendor;
        this.speechSynthesisVoice = speechSynthesisVoice;
        this.speechRecognizerVendor = speechRecognizerVendor;
        this.speechRecognizerLanguage = speechRecognizerLanguage;
        this.callHook = callHook;
        this.callStatusHook = callStatusHook;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String applicationSid;
        private String name;
        private String accountSid;
        private String speechSynthesisVendor;
        private String speechSynthesisVoice;
        private String speechRecognizerVendor;
        private String speechRecognizerLanguage;
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

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder accountSid(String accountSid) {
            this.accountSid = accountSid;
            return this;
        }

        public Builder speechSynthesisVendor(String speechSynthesisVendor) {
            this.speechSynthesisVendor = speechSynthesisVendor;
            return this;
        }

        public Builder speechSynthesisVoice(String speechSynthesisVoice) {
            this.speechSynthesisVoice = speechSynthesisVoice;
            return this;
        }

        public Builder speechRecognizerVendor(String speechRecognizerVendor) {
            this.speechRecognizerVendor = speechRecognizerVendor;
            return this;
        }

        public Builder speechRecognizerLanguage(String speechRecognizerLanguage) {
            this.speechRecognizerLanguage = speechRecognizerLanguage;
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


        public ApplicationPutRequest build() {
            return new ApplicationPutRequest(applicationSid, name, accountSid, speechSynthesisVendor, speechSynthesisVoice, speechRecognizerVendor, speechRecognizerLanguage, callHook, callStatusHook);
        }
    }

}
