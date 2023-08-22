package com.education.model.enumEntity;

public enum EnumFileType {
    MAIN("MAIN"),
    FACSIMILE("FACSIMILE");

    private final String value;

    EnumFileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
