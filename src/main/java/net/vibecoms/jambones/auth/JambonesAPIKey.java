package net.vibecoms.jambones.auth;

public class JambonesAPIKey implements JambonesKeyProvider {

    private final String key;

    public JambonesAPIKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
