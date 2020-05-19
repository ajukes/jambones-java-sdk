package net.vibecoms.jambones.exceptions;

import java.io.IOException;

public class JambonesRestException extends IOException {
    public JambonesRestException(String message) {
        super(message);
    }
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
