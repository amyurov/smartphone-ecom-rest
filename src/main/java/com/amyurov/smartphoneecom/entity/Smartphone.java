package com.amyurov.smartphoneecom.entity;

import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Manufacturer manufacturer;

    private String model;

    private String color;

    @Column(name = "memory_size")
    private Integer memorySize;

    private BigDecimal price;
}
