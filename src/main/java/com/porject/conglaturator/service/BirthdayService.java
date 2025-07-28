package com.porject.conglaturator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.porject.conglaturator.dto.BirthdayRequestDTO;
import com.porject.conglaturator.dto.BirthdayResponseDTO;
import com.porject.conglaturator.model.BirthdayEntity;
import com.porject.conglaturator.repository.BirthdayRepository;

@Service
public class BirthdayService {
    private final BirthdayRepository repository;

    public BirthdayService(BirthdayRepository repository) {
        this.repository = repository;
    }

    public List<BirthdayResponseDTO> getAllBirthdays() {
        return repository.findAll().stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public List<BirthdayResponseDTO> getUpcomingBirthdays() {
        return repository.findUpcomingBirthdays().stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public BirthdayResponseDTO addBirthday(BirthdayRequestDTO requestDTO) {
        BirthdayEntity birthday = new BirthdayEntity();
        birthday.setName(requestDTO.getName());
        birthday.setDate(requestDTO.getDate());
        return toResponseDTO(repository.save(birthday));
    }

    public BirthdayResponseDTO updateBirthday(Long id, BirthdayRequestDTO requestDTO) {
        BirthdayEntity existing = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Birthday not found"));
        existing.setName(requestDTO.getName());
        existing.setDate(requestDTO.getDate());
        return toResponseDTO(repository.save(existing));
    }

    public void deleteBirthday(Long id) {
        repository.deleteById(id);
    }

    private BirthdayResponseDTO toResponseDTO(BirthdayEntity birthday) {
        BirthdayResponseDTO responseDTO = new BirthdayResponseDTO();
        responseDTO.setId(birthday.getId());
        responseDTO.setName(birthday.getName());
        responseDTO.setDate(birthday.getDate());
        return responseDTO;
    }
}