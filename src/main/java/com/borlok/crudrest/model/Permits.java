package com.borlok.crudrest.model;

public enum  Permits {
    AUTHORITY_READ("authority:read"),
    AUTHORITY_WRITE("authority:write");

    private String permit;

    Permits(String permit) {
        this.permit = permit;
    }

    public String getPermit() {
        return permit;
    }
}
