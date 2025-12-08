package com.example.todolist.controller;

import com.example.todolist.dto.TodoRequestDto;
import com.example.todolist.dto.TodoResponseDto;
import com.example.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 날짜별 Todo 조회
    @GetMapping
    public List<TodoResponseDto> getTodosByDate(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return todoService.getTodosByDate(date);
    }

    // Todo 추가
    @PostMapping
    public TodoResponseDto addTodo(@Valid @RequestBody TodoRequestDto todoRequestdto) {
        return todoService.save(todoRequestdto);
    }

    // Todo 수정
    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequestDto todoRequestdto
    ) {
        return todoService.update(id, todoRequestdto);
    }

    // Todo 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
    }
}
