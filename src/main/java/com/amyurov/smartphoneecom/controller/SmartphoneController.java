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
/**
 * Класс {@code SmartphoneController} предоставляет RESTful эндпоинты для обработки CRUD
 * операций над смартфонами.
 * Использует {@link SmartphoneService} для выполнения бизнес-логики.
 */
@RestController
@RequestMapping("/api/v1/smartphones")
@RequiredArgsConstructor
@Slf4j
public class SmartphoneController {

    private static final String BASE_URI = "api/v1/smartphones/";
    private final SmartphoneService smartphoneService;

    /**
     * Получает список всех смартфонов.
     * @return {@link ContentResponse} - обертка содержащая список {@link SmartphoneReadDto}.
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        var smartphoneList = smartphoneService.findAll();
        return ResponseEntity.ok(new ContentResponse(smartphoneList));
    }

    /**
     * Получает список всех смартфонов с пагинацией.
     *
     * @param pageable Содержит информацию о пагинации.
     * @return {@link PaginatedContentResponse} обертка содержащая пагинированый список {@link SmartphoneReadDto}.
     */
    @GetMapping(params = {"size"})
    public ResponseEntity<?> findAllPaginated(Pageable pageable) {
        Page<SmartphoneReadDto> allPaginated = smartphoneService.findAllPaginated(pageable);

        return ResponseEntity.ok(PaginatedContentResponse.of(allPaginated));
    }

    /**
     * Получает смартфон по его ID.
     *
     * @param id ID смартфона, который нужно получить.
     * @return {@link ContentResponse} - обертка содержащая запрашиваемый {@link SmartphoneReadDto}, или
     * 404 (Not Found) если смартфон не найден.
     * @throws ResponseStatusException если смартфон не найден.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        var readDto = smartphoneService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(new ContentResponse(List.of(readDto)));
    }

    /**
     * Создает новый смартфон.
     *
     * @param smartphone Данные создаваемого смартфона.
     * @return ResponseEntity, содержащий сохраненный смартфон.
     * @throws ResponseStatusException при ошибке сохранения.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated SmartphoneCreateDto smartphone) {
        var smartphoneReadDto = smartphoneService.create(smartphone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        URI createdUri = URI.create(BASE_URI + smartphoneReadDto.getId());
        return ResponseEntity.created(createdUri).body(smartphoneReadDto);
    }

    /**
     * Обновляет существующий смартфон.
     *
     * @param id ID обновляемого смартфона.
     * @param smartphone Обновленные данные смартфона.
     * @return ResponseEntity, указывающий на статус обновления.
     * @throws ResponseStatusException если обновление не удалось.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update (@PathVariable Integer id, @RequestBody @Validated SmartphoneEditDto smartphone) {
        smartphoneService.update(id, smartphone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_MODIFIED));

        return ResponseEntity.ok().build();
    }
    /**
     * Удаляет смартфон по его ID.
     *
     * @param id ID удаляемого смартфона.
     * @return ResponseEntity 204 (No Content) если удаление успешно, или 404 (Not Found) если смартфон не найден.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return smartphoneService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
