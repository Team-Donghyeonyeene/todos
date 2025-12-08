package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TodoRequestDto {

    @NotBlank(message = "할 일을 입력해야 합니다.")
    private String task;

    private boolean completed;

    @NotNull(message = "날짜를 선택해야 합니다.")
    private LocalDate date;
}
