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

import com.porject.conglaturator.model.BirthdayEntity;
import com.porject.conglaturator.service.BirthdayService;

@RestController
@RequestMapping("/api/birthdays")
public class BirthdayController {
    private final BirthdayService service;

    public BirthdayController(BirthdayService service) {
        this.service = service;
    }

    @GetMapping
    public List<BirthdayEntity> getAllBirthdays() {
        return service.getAllBirthdays();
    }

    @GetMapping("/upcoming")
    public List<BirthdayEntity> getUpcomingBirthdays() {
        return service.getUpcomingBirthdays();
    }

    @GetMapping("/current")
    public List<BirthdayEntity> getCurrentBirthdays() {
        return service.getCurrentBirthdays();
    }

    @PostMapping
    public BirthdayEntity addBirthday(@RequestBody BirthdayEntity birthday) {
        return service.addBirthday(birthday);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirthdayEntity> updateBirthday(@PathVariable Long id, @RequestBody BirthdayEntity birthday) {
        return ResponseEntity.ok(service.updateBirthday(id, birthday));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthday(@PathVariable Long id) {
        service.deleteBirthday(id);
        return ResponseEntity.noContent().build();
    }
}