package com.github.evertonbrunosds.shop.util;

import java.util.function.Supplier;

public final class Converter {

    public static final <O> SafeGetter<O> convertTo(final Supplier<O> procedure) {
        return new SafeGetter<O>() {

            @Override
            public <T extends Throwable> O orThrow(final Supplier<T> exception) throws T {
                try {
                    return procedure.get();
                } catch (final Throwable throwable) {
                    throw exception.get();
                }
            }

        };
    }

    public interface SafeGetter<O> {

        public <T extends Throwable> O orThrow(final Supplier<T> supplier) throws T;

    }

}
