package com.amyurov.smartphoneecom.service;

import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.entity.Smartphone;
import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import com.amyurov.smartphoneecom.repository.SmartphoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SmartphoneServiceTest {

    @Mock
    private SmartphoneRepository smartphoneRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private SmartphoneService smartphoneService;

    private static Smartphone smartphone;
    private static SmartphoneReadDto smartphoneReadDto;

    @BeforeAll()
    public static void setup() {
        smartphone = new Smartphone(1, Manufacturer.APPLE, "Iphone XR", "Black", 256,
                BigDecimal.valueOf(599.99));
        smartphoneReadDto = new SmartphoneReadDto(1, Manufacturer.APPLE, "Iphone XR", "Black", 256,
                BigDecimal.valueOf(599.99));
    }

    @Test
    void findAll() {
        when(smartphoneRepository.findAll()).thenReturn(List.of(smartphone));
        when(modelMapper.map(smartphone, SmartphoneReadDto.class)).thenReturn(smartphoneReadDto);

        var expected = List.of(smartphoneReadDto);
        var actual = smartphoneService.findAll();

        Assertions.assertEquals(expected, actual);
        verify(smartphoneRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(smartphone, SmartphoneReadDto.class);
    }

    @Test
    void findAllPaginated() {
        var pageRequest = PageRequest.of(0, 1, Sort.by("price"));

        when(smartphoneRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of(smartphone)));
        when(modelMapper.map(smartphone, SmartphoneReadDto.class)).thenReturn(smartphoneReadDto);

        var expected = new PageImpl<>(List.of(smartphoneReadDto));
        var actual = smartphoneService.findAllPaginated(pageRequest);

        Assertions.assertEquals(expected, actual);
        verify(smartphoneRepository, times(1)).findAll(pageRequest);
        verify(modelMapper, times(1)).map(smartphone, SmartphoneReadDto.class);
    }

    @Test
    void findById() {
        when(smartphoneRepository.findById(1)).thenReturn(Optional.of(smartphone));
        when(modelMapper.map(smartphone, SmartphoneReadDto.class)).thenReturn(smartphoneReadDto);

        var expected = Optional.of(smartphoneReadDto);
        var actual = smartphoneService.findById(1);

        Assertions.assertEquals(expected, actual);
        verify(smartphoneRepository, times(1)).findById(1);
        verify(modelMapper, times(1)).map(smartphone, SmartphoneReadDto.class);
    }


}