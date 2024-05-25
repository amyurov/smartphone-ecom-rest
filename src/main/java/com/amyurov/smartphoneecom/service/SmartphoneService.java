package com.amyurov.smartphoneecom.service;

import com.amyurov.smartphoneecom.dto.SmartphoneCreateDto;
import com.amyurov.smartphoneecom.dto.SmartphoneEditDto;
import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.entity.Smartphone;
import com.amyurov.smartphoneecom.repository.SmartphoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
/**
 * Класс {@code SmartphoneService} предоставляет методы для управления смартфонами.
 * Использует {@link SmartphoneRepository} для взаимодействия с базой данных.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private final ModelMapper modelMapper;

    /**
     * Получает список всех смартфонов.
     *
     * @return Список объектов {@link SmartphoneReadDto}.
     */
    public List<SmartphoneReadDto> findAll() {
        return smartphoneRepository.findAll().stream()
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class))
                .toList();
    }

    /**
     * Получает список всех смартфонов с пагинацией.
     *
     * @param pageable Содержит информацию о пагинации.
     * @return Страница объектов {@link SmartphoneReadDto}.
     */
    public Page<SmartphoneReadDto> findAllPaginated(Pageable pageable) {
        return smartphoneRepository.findAll(pageable)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    /**
     * Получает смартфон по его ID.
     *
     * @param id ID смартфона, который нужно получить.
     * @return Optional.of {@link SmartphoneReadDto} если смартфон найден.
     */
    public Optional<SmartphoneReadDto> findById(Integer id) {
        return smartphoneRepository.findById(id)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    /**
     * Создает новый смартфон.
     *
     * @param smartphone Данные создаваемого смартфона.
     * @return Optional.of {@link SmartphoneReadDto} если создание успешно.
     */
    @Transactional
    public Optional<SmartphoneReadDto> create(SmartphoneCreateDto smartphone) {
        return Optional.of(smartphone)
                .map(i -> modelMapper.map(i, Smartphone.class))
                .map(smartphoneRepository::save)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    /**
     * Обновляет существующий смартфон.
     *
     * @param id ID обновляемого смартфона.
     * @param dto Обновленные данные смартфона.
     * @return Optional.of {@link SmartphoneReadDto} если обновление успешно.
     */
    @Transactional
    public Optional<SmartphoneReadDto> update(Integer id, SmartphoneEditDto dto) {
        var smartphone = smartphoneRepository.findById(id);
        modelMapper.map(dto, smartphone);
        return smartphone
                .map(smartphoneRepository::saveAndFlush)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    /**
     * Удаляет смартфон по его ID.
     *
     * @param id ID удаляемого смартфона.
     * @return {@code true}, если смартфон успешно удален, иначе {@code false}.
     */
    @Transactional
    public boolean delete(Integer id) {
        return smartphoneRepository.findById(id)
                .map(i -> {
                    smartphoneRepository.delete(i);
                    return true;
                }).orElse(false);
    }
}