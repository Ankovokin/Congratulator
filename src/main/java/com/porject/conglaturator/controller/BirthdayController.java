package com.porject.conglaturator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porject.conglaturator.dto.BirthdayRequestDTO;
import com.porject.conglaturator.dto.BirthdayResponseDTO;
import com.porject.conglaturator.model.BirthdayEntity;
import com.porject.conglaturator.service.BirthdayService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/birthdays")
@Slf4j
public class BirthdayController {
   private final BirthdayService service;

    public BirthdayController(BirthdayService service) {
        this.service = service;
    }

    @GetMapping
    public List<BirthdayResponseDTO> getAllBirthdays() {
        log.info("Received request to fetch all birthdays");
        return service.getAllBirthdays();
    }

    @GetMapping("/upcoming")
    public List<BirthdayResponseDTO> getUpcomingBirthdays() {
        log.info("Received request to fetch upcoming birthdays");
        return service.getUpcomingBirthdays();
    }

    @GetMapping("/current")
    public List<BirthdayEntity> getCurrentBirthdays() {
        return service.getCurrentBirthdays();
    }

    @PostMapping
    public BirthdayResponseDTO addBirthday(@RequestBody BirthdayRequestDTO requestDTO) {
        log.info("Received request to add birthday for name: {}", requestDTO.getName());
        return service.addBirthday(requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirthdayResponseDTO> updateBirthday(@PathVariable Long id, @RequestBody BirthdayRequestDTO requestDTO) {
        log.info("Received request to update birthday with ID: {}", id);
        return ResponseEntity.ok(service.updateBirthday(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthday(@PathVariable Long id) {
        log.info("Received request to delete birthday with ID: {}", id);
        service.deleteBirthday(id);
        return ResponseEntity.noContent().build();
    }
}