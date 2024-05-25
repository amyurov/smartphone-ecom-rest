package com.amyurov.smartphoneecom.controller;

import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.entity.enums.Manufacturer;
import com.amyurov.smartphoneecom.service.SmartphoneService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SmartphoneController.class)
class SmartphoneControllerTest {

    private static final String BASE_URI = "/api/v1/smartphones";

    @MockBean
    private SmartphoneService smartphoneService;

    @Autowired
    private MockMvc mockMvc;

    private static SmartphoneReadDto smartphoneReadDto;

    @BeforeAll()
    public static void setup() {
        smartphoneReadDto = new SmartphoneReadDto(1, Manufacturer.APPLE, "Iphone XR", "Black", 256,
                BigDecimal.valueOf(599.99));
    }

    @Test
    public void findAll_correctRequest() throws Exception {
        when(smartphoneService.findAll()).thenReturn(List.of(smartphoneReadDto));

        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].manufacturer", is("Apple")));

        verify(smartphoneService, times(1)).findAll();
    }

    @Test
    public void findAll_correctPageableRequest() throws Exception {
        var pageRequest = PageRequest.of(0, 1, Sort.by("price"));
        when(smartphoneService.findAllPaginated(pageRequest)).thenReturn(
                new PageImpl<>(List.of(smartphoneReadDto), pageRequest, 1L));
        mockMvc.perform(get(BASE_URI + "?size={size}&sort={sort}", 1, "price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metaData").exists())
                .andExpect(jsonPath("$.metaData.size", is(1)))
                .andExpect(jsonPath("$.metaData.page", is(0)))
                .andExpect(jsonPath("$.metaData.sort", is("price: ASC")))
                .andExpect(jsonPath("$.content", hasSize(1)));

        verify(smartphoneService, times(1)).findAllPaginated(pageRequest);
    }
}