package com.amyurov.smartphoneecom.service;

import com.amyurov.smartphoneecom.dto.SmartphoneCreateDto;
import com.amyurov.smartphoneecom.dto.SmartphoneEditDto;
import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.entity.Smartphone;
import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import com.amyurov.smartphoneecom.repository.SmartphoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
    private static SmartphoneCreateDto smartphoneCreateDto;
    private static SmartphoneEditDto smartphoneEditDto;
    private static Smartphone smartphoneEdited;

    @BeforeAll()
    public static void setup() {
        smartphone = new Smartphone(1, Manufacturer.APPLE, "Iphone XR", "Black", 256,
                BigDecimal.valueOf(599.99));
        smartphoneReadDto = new SmartphoneReadDto(1, Manufacturer.APPLE, "Iphone XR", "Black", 256,
                BigDecimal.valueOf(599.99));
        smartphoneCreateDto = new SmartphoneCreateDto(Manufacturer.APPLE, "Iphone XR", "Black", 256,
                BigDecimal.valueOf(599.99));
        smartphoneEditDto = new SmartphoneEditDto(null, null, null, null, BigDecimal.valueOf(1099.99));
        smartphoneEdited = new Smartphone(1, Manufacturer.APPLE, "Iphone XR", "Black", 256,
                BigDecimal.valueOf(1099.99));
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

    @Test
    void create() {
        when(modelMapper.map(smartphoneCreateDto, Smartphone.class)).thenReturn(smartphone);
        when(smartphoneRepository.save(smartphone)).thenReturn(smartphone);
        when(modelMapper.map(smartphone, SmartphoneReadDto.class)).thenReturn(smartphoneReadDto);

        var expected = Optional.of(smartphoneReadDto);
        var actual = smartphoneService.create(smartphoneCreateDto);

        Assertions.assertEquals(expected, actual);
        verify(modelMapper, times(1)).map(smartphoneCreateDto, Smartphone.class);
        verify(smartphoneRepository, times(1)).save(smartphone);
        verify(modelMapper, times(1)).map(smartphone, SmartphoneReadDto.class);
    }


    @Test
    void delete() {
        when(smartphoneRepository.findById(1)).thenReturn(Optional.of(smartphone));

        var expected = true;
        var actual = smartphoneService.delete(1);

        Assertions.assertEquals(expected, actual);
        verify(smartphoneRepository, times(1)).delete(smartphone);

    }

    @Test
    @Disabled
    void update() {
        when(smartphoneRepository.findById(1)).thenReturn(Optional.of(smartphone));
        when(smartphoneRepository.saveAndFlush(smartphoneEdited)).thenReturn(smartphoneEdited);
        when(modelMapper.map(smartphoneEdited, SmartphoneReadDto.class)).thenReturn(smartphoneReadDto);

        var expected = Optional.of(smartphoneReadDto);
        var actual = smartphoneService.update(1, smartphoneEditDto);

        Assertions.assertEquals(expected, actual);
        verify(smartphoneRepository, times(1)).findById(1);
        verify(modelMapper, times(1)).map(smartphoneEditDto, smartphone);
        verify(modelMapper, times(1)).map(smartphone, SmartphoneReadDto.class);
    }

}