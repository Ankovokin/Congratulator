package com.porject.conglaturator.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BirthdayResponseDTO {
    private Long id;
    private String name;
    private LocalDate date;
}