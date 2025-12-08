package com.example.todolist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemoRequestDto {

    @NotNull(message = "날짜는 필수입니다.")
    private LocalDate date;

    private String content;
}
