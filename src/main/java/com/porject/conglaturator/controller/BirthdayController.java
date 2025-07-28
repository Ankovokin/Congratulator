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
import com.porject.conglaturator.service.BirthdayService;

@RestController
@RequestMapping("/api/birthdays")
public class BirthdayController {
    private final BirthdayService service;

    public BirthdayController(BirthdayService service) {
        this.service = service;
    }

    @GetMapping
    public List<BirthdayResponseDTO> getAllBirthdays() {
        return service.getAllBirthdays();
    }

    @GetMapping("/upcoming")
    public List<BirthdayResponseDTO> getUpcomingBirthdays() {
        return service.getUpcomingBirthdays();
    }

    @PostMapping
    public BirthdayResponseDTO addBirthday(@RequestBody BirthdayRequestDTO requestDTO) {
        return service.addBirthday(requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirthdayResponseDTO> updateBirthday(@PathVariable Long id, @RequestBody BirthdayRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateBirthday(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthday(@PathVariable Long id) {
        service.deleteBirthday(id);
        return ResponseEntity.noContent().build();
    }
}