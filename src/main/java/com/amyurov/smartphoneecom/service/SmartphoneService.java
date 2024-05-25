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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private final ModelMapper modelMapper;

    public List<SmartphoneReadDto> findAll() {
        return smartphoneRepository.findAll().stream()
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class))
                .toList();
    }

    public Page<SmartphoneReadDto> findAllPaginated(Pageable pageable) {
        return smartphoneRepository.findAll(pageable)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    public Optional<SmartphoneReadDto> findById(Integer id) {
        return smartphoneRepository.findById(id)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    @Transactional
    public Optional<SmartphoneReadDto> create(SmartphoneCreateDto smartphone) {
        return Optional.of(smartphone)
                .map(i -> modelMapper.map(i, Smartphone.class))
                .map(smartphoneRepository::save)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    @Transactional
    public Optional<SmartphoneReadDto> update(Integer id, SmartphoneEditDto dto) {
        var smartphone = smartphoneRepository.findById(id);
        modelMapper.map(dto, smartphone);
        return smartphone
                .map(smartphoneRepository::saveAndFlush)
                .map(i -> modelMapper.map(i, SmartphoneReadDto.class));
    }

    @Transactional
    public boolean delete(Integer id) {
        return smartphoneRepository.findById(id)
                .map(i -> {
                    smartphoneRepository.delete(i);
                    return true;
                }).orElse(false);
    }
}