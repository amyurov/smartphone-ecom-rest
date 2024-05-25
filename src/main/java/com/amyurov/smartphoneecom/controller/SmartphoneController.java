package com.amyurov.smartphoneecom.controller;

import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.response.ContentResponse;
import com.amyurov.smartphoneecom.service.SmartphoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/smartphones")
@RequiredArgsConstructor
@Slf4j
public class SmartphoneController {

    private static final String BASE_URI = "api/v1/smartphones/";
    private final SmartphoneService smartphoneService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        var smartphoneList = smartphoneService.findAll();
        return ResponseEntity.ok(new ContentResponse(smartphoneList));
    }

}
