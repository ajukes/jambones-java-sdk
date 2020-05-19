package net.vibecoms.jambones.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class JambonesConstraintViolation extends ConstraintViolationException {

    public JambonesConstraintViolation(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
