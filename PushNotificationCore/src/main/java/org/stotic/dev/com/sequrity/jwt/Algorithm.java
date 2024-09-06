package org.stotic.dev.com.sequrity.jwt;

public enum Algorithm {

    ES256("ES256");

    Algorithm(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
