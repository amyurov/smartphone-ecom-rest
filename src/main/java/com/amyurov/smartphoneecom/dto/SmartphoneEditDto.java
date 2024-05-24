package com.amyurov.smartphoneecom.dto;

import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SmartphoneEditDto(
        Manufacturer manufacturer,
        String model,
        String color,
        @Positive Integer memorySize,
        @Positive BigDecimal price) {
}
