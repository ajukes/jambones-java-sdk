package net.vibecoms.jambones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class APIKeyPostRequest {

    @NotNull
    @NotBlank
    @JsonProperty("service_provider_sid")
    private String serviceProviderSid;

    @NotNull
    @NotBlank
    @JsonProperty("account_sid")
    private String accountSid;

    private APIKeyPostRequest() {
    }
}
