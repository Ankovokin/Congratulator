package com.porject.conglaturator.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BirthdayRequestDTO {
    private String name;
    private LocalDate date;
}
