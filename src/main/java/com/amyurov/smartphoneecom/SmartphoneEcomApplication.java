package com.amyurov.smartphoneecom;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartphoneEcomApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartphoneEcomApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.registerModule(new RecordModule());
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }

}
