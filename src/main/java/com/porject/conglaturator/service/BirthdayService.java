package com.porject.conglaturator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.porject.conglaturator.model.BirthdayEntity;
import com.porject.conglaturator.repository.BirthdayRepository;

@Service
public class BirthdayService {
    private final BirthdayRepository repository;

    public BirthdayService(BirthdayRepository repository) {
        this.repository = repository;
    }

    public List<BirthdayEntity> getAllBirthdays() {
        return repository.findAll();
    }

    public List<BirthdayEntity> getUpcomingBirthdays() {
        return repository.findUpcomingBirthdays();
    }

    public List<BirthdayEntity> getCurrentBirthdays() {
        return repository.findCurrentBirthdays();
    }

    public BirthdayEntity addBirthday(BirthdayEntity birthday) {
        return repository.save(birthday);
    }

    public BirthdayEntity updateBirthday(Long id, BirthdayEntity updatedBirthday) {
        BirthdayEntity existing = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Birthday not found"));
        existing.setName(updatedBirthday.getName());
        existing.setDate(updatedBirthday.getDate());
        return repository.save(existing);
    }

    public void deleteBirthday(Long id) {
        repository.deleteById(id);
    }
}