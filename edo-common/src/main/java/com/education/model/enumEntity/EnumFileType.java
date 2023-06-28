package com.education.model.enumEntity;

public enum EnumFileType {
    MAIN,
    FACSIMILE;

    private String value;

    EnumFileType(String value) {
        this.value = value;
    }

    EnumFileType() {

    }

    public String getValue() {
        return this.value;
    }
}
