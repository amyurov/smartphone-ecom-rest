package com.amyurov.smartphoneecom.service;

import com.amyurov.smartphoneecom.dto.SmartphoneCreateDto;
import com.amyurov.smartphoneecom.dto.SmartphoneReadDto;
import com.amyurov.smartphoneecom.repository.SmartphoneRepository;
import lombok.RequiredArgsConstructor;
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

    public List<SmartphoneReadDto> findAll() {
        return Collections.emptyList();
    }

    public Page<SmartphoneReadDto> findAllPaginated(Pageable pageable) {
        return null;
    }

    public Optional<SmartphoneReadDto> findById(Integer id) {
        return Optional.empty();
    }

    public Optional<SmartphoneReadDto> create(SmartphoneCreateDto smartphone) {
        return Optional.empty();
    }
}