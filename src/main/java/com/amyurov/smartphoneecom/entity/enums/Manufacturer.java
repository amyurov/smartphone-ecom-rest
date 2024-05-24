package com.amyurov.smartphoneecom.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Manufacturer {
    APPLE,
    XIAOMI,
    SAMSUNG;

    @JsonValue
    public String getValue() {
        String lowerCase = this.name().toLowerCase();
        return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
    }
}