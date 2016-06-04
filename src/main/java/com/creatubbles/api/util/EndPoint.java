package com.creatubbles.api.util;

public interface EndPoint {

    String getTemplate();

    String format(Object... args);

    /**
     * Should return {@link #getTemplate()}.
     */
    @Override
    String toString();
}
