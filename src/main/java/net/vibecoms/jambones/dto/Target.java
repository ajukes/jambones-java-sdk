package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Target {
    @NotEmpty
    @JsonProperty("type")
    private String type;
    @JsonProperty("number")
    private String number;
    @JsonProperty("sipUri")
    private String sipUri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("auth")
    private TargetAuth auth;

    public Target(String type, String number, String sipUri, String name, TargetAuth auth) {
        this.type = type;
        this.number = number;
        this.sipUri = sipUri;
        this.name = name;
        this.auth = auth;
    }

    public static Target phone(String number) {
        return new Target("phone", number, null, null, null);
    }

    public static Target sip(String sipUri) {
        return new Target("sip", null, sipUri, null, null);
    }

    public static Target user(String username) {
        return new Target("sip", null, null, username, null);
    }

    public static Target user(String username, String authUserName, String authPassword) {
        return new Target("sip", null, null, username, new TargetAuth(authUserName, authPassword));
    }
}
