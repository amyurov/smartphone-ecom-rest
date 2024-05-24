package com.amyurov.smartphoneecom.converters;

import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ManufacturerNameConverter implements AttributeConverter<Manufacturer, String> {
    @Override
    public String convertToDatabaseColumn(Manufacturer attribute) {
        if (attribute == null) {
            return null;
        }
        String lowerCase = attribute.name().toLowerCase();
        return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
    }

    @Override
    public Manufacturer convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Manufacturer.valueOf(dbData.toUpperCase());
    }
}
