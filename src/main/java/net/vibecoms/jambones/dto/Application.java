package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Application {

    @JsonProperty("application_sid")
    private String applicationSid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("account_sid")
    private String accountSid;

    @JsonProperty("speech_synthesis_vendor")
    private String speechSynthesisVendor;

    @JsonProperty("speech_synthesis_voice")
    private String speechSynthesisVoice;

    /* Depreciated */
    @JsonProperty("speech_synthesis_language")
    private String speechSynthesisLanguage;

    @JsonProperty("speech_recognizer_vendor")
    private String speechRecognizerVendor;

    @JsonProperty("speech_recognizer_language")
    private String speechRecognizerLanguage;

    @JsonProperty("call_hook")
    private Hook callHook;

    @JsonProperty("call_status_hook")
    private Hook callStatusHook;


}
