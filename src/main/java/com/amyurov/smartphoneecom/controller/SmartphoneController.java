package com.amyurov.smartphoneecom.controller;

import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.response.ContentResponse;
import com.amyurov.smartphoneecom.response.PaginatedContentResponse;
import com.amyurov.smartphoneecom.service.SmartphoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(params = {"size"})
    public ResponseEntity<?> findAllPaginated(Pageable pageable) {
        Page<SmartphoneReadDto> allPaginated = smartphoneService.findAllPaginated(pageable);

        return ResponseEntity.ok(PaginatedContentResponse.of(allPaginated));
    }

}
