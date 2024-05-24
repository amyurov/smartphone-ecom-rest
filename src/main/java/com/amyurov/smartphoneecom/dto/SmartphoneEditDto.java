package com.amyurov.smartphoneecom.dto;

import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import com.amyurov.smartphoneecom.validation.NotBlankOrNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SmartphoneEditDto(
        Manufacturer manufacturer,
        @NotBlankOrNull String model,
        @NotBlankOrNull String color,
        @Positive Integer memorySize,
        @Positive BigDecimal price) {
}
