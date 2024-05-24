package com.amyurov.smartphoneecom.dto;

import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SmartphoneCreateDto(@NotNull Manufacturer manufacturer,
                                  @NotBlank @NotNull String model,
                                  @NotBlank @NotNull String color,
                                  @Positive @NotNull Integer memorySize,
                                  @Positive @NotNull BigDecimal price) {
}
