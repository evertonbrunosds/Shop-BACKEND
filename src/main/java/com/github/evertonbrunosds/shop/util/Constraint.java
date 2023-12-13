package com.github.evertonbrunosds.shop.util;

import java.net.URI;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

public class Constraint {

    private static final String EMAIL_FORMAT = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public final static Thrower ifNotEmail(final String email) {
        return ifTrue(!Pattern.compile(EMAIL_FORMAT).matcher(email).matches());
    }

    public final static Thrower ifEmail(final String email) {
        return ifTrue(Pattern.compile(EMAIL_FORMAT).matcher(email).matches());
    }

    public final static Thrower ifNotBlank(final String target) {
        return ifTrue(target != null ? !target.isBlank() : false);
    }

    public final static Thrower ifBlank(final String target) {
        return ifTrue(target == null ? true : target.isBlank());
    }

    public final static Thrower ifNull(final Object target) {
        return ifTrue(target == null);
    }

    public final static Thrower ifNotNull(final Object target) {
        return ifTrue(target != null);
    }

    public final static Thrower ifFalse(final boolean target) {
        return ifTrue(!target);
    }

    public final static Thrower ifNotURL(final String target) {
        try {
            final var uri = new URI(target);
            uri.toURL();
            return ifTrue(Boolean.FALSE);
        } catch (final Throwable exception) {
            return ifTrue(Boolean.TRUE);
        }
    }

    public final static Thrower ifTrue(final boolean target) {
        return new Thrower() {

            @Override
            public void throwResourceException(final HttpStatus involvedStatus, final Parameter involvedParameter, final Class<?> involvedClass) {
                if (target) {
                    throw new ResourceException(involvedStatus, involvedParameter, involvedClass);
                }
            }

        };
    }

    @FunctionalInterface
    public interface Thrower {

        public void throwResourceException(final HttpStatus involvedStatus, final Parameter involvedParameter, final Class<?> involvedClass);

    }

}
