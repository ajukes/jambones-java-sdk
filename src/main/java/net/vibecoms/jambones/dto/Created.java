package net.vibecoms.jambones.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Created {
    private String sid;
    private String token;
    private String user_sid;
}
