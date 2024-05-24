package com.amyurov.smartphoneecom.dto;

import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SmartphoneReadDto {

    Integer id;
    Manufacturer manufacturer;
    String model;
    String color;
    Integer memorySize;
    BigDecimal price;
}
