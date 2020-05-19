package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hook {

    @JsonProperty("webhook_sid")
    private String webhookSid;

    @JsonProperty("url")
    private String url;

    @JsonProperty("method")
    private String method;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public Hook(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public Hook(String url, String method, String username, String password) {
        this.url = url;
        this.method = method;
        this.username = username;
        this.password = password;
    }
}
