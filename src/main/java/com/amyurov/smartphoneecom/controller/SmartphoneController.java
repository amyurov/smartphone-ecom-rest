package com.amyurov.smartphoneecom.controller;

import com.amyurov.smartphoneecom.dto.SmartphoneCreateDto;
import com.amyurov.smartphoneecom.dto.SmartphoneEditDto;
import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.response.ContentResponse;
import com.amyurov.smartphoneecom.response.PaginatedContentResponse;
import com.amyurov.smartphoneecom.service.SmartphoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        var readDto = smartphoneService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(new ContentResponse(List.of(readDto)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated SmartphoneCreateDto smartphone) {
        var smartphoneReadDto = smartphoneService.create(smartphone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        URI createdUri = URI.create(BASE_URI + smartphoneReadDto.getId());
        return ResponseEntity.created(createdUri).body(smartphoneReadDto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update (@PathVariable Integer id, @RequestBody @Validated SmartphoneEditDto smartphone) {
        smartphoneService.update(id, smartphone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_MODIFIED));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return smartphoneService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
