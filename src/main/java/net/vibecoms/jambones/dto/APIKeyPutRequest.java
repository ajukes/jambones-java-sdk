package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIKeyPutRequest {

    /*
    * No updating of APIKey is possible so
    * make constructor private so cannot be
    * instantiated
     */
    private APIKeyPutRequest() {
        throw new AssertionError("Cannot instantiate APIKeyPutRequest");
    }

}
