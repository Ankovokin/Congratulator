package com.porject.conglaturator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.porject.conglaturator.model.BirthdayEntity;

public interface BirthdayRepository extends JpaRepository<BirthdayEntity, Long> {
    @Query("SELECT b FROM BirthdayEntity b WHERE MONTH(b.date) = MONTH(CURRENT_DATE) AND DAY(b.date) >= DAY(CURRENT_DATE) ORDER BY MONTH(b.date), DAY(b.date)")
    List<BirthdayEntity> findUpcomingBirthdays();

    @Query("SELECT b FROM BirthdayEntity b WHERE MONTH(b.date) = MONTH(CURRENT_DATE) AND DAY(b.date) = DAY(CURRENT_DATE)")
    List<BirthdayEntity> findCurrentBirthdays();
}