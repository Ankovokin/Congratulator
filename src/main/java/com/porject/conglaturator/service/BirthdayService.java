package com.porject.conglaturator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.porject.conglaturator.dto.BirthdayRequestDTO;
import com.porject.conglaturator.dto.BirthdayResponseDTO;
import com.porject.conglaturator.model.BirthdayEntity;
import com.porject.conglaturator.repository.BirthdayRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BirthdayService {
private final BirthdayRepository repository;

    public BirthdayService(BirthdayRepository repository) {
        this.repository = repository;
    }

    public List<BirthdayResponseDTO> getAllBirthdays() {
        log.info("Fetching all birthdays");
        return repository.findAll().stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public List<BirthdayResponseDTO> getUpcomingBirthdays() {
        log.info("Fetching upcoming birthdays");
        return repository.findUpcomingBirthdays().stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public List<BirthdayEntity> getCurrentBirthdays() {
        return repository.findCurrentBirthdays();
    } 

    public BirthdayResponseDTO addBirthday(BirthdayRequestDTO requestDTO) {
        log.info("Adding new birthday for name: {}", requestDTO.getName());
        BirthdayEntity birthday = new BirthdayEntity();
        birthday.setName(requestDTO.getName());
        birthday.setDate(requestDTO.getDate());
        BirthdayEntity saved = repository.save(birthday);
        log.info("Successfully added birthday with ID: {}", saved.getId());
        return toResponseDTO(saved);
    }

    public BirthdayResponseDTO updateBirthday(Long id, BirthdayRequestDTO requestDTO) {
        log.info("Updating birthday with ID: {}", id);
        BirthdayEntity existing = repository.findById(id)
            .orElseThrow(() -> {
                log.error("Birthday not found with ID: {}", id);
                return new IllegalArgumentException("Birthday not found");
            });
        existing.setName(requestDTO.getName());
        existing.setDate(requestDTO.getDate());
        BirthdayEntity updated = repository.save(existing);
        log.info("Successfully updated birthday with ID: {}", id);
        return toResponseDTO(updated);
    }

    public void deleteBirthday(Long id) {
        log.info("Deleting birthday with ID: {}", id);
        repository.deleteById(id);
        log.info("Successfully deleted birthday with ID: {}", id);
    }

    private BirthdayResponseDTO toResponseDTO(BirthdayEntity birthday) {
        BirthdayResponseDTO responseDTO = new BirthdayResponseDTO();
        responseDTO.setId(birthday.getId());
        responseDTO.setName(birthday.getName());
        responseDTO.setDate(birthday.getDate());
        return responseDTO;
    }
}