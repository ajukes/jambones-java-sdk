package net.vibecoms.jambones.auth;

import lombok.ToString;

@ToString
public class JambonesAdminKey implements JambonesKeyProvider {

    private final String key;

    public JambonesAdminKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
