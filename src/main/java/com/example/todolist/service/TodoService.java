package com.example.todolist.service;

import com.example.todolist.dto.TodoRequestDto;
import com.example.todolist.dto.TodoResponseDto;
import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 날짜별 Todo 조회
    public List<TodoResponseDto> getTodosByDate(LocalDate date) {
        List<Todo> todos = todoRepository.findAllByDateOrderByIdAsc(date);
        return todos.stream()
                .map(this::toDto)
                .toList();
    }

    // Todo 생성
    public TodoResponseDto save(TodoRequestDto request) {
        LocalDate date = request.getDate();
        if (date == null) {
            date = LocalDate.now();
        }

        Todo todo = Todo.builder()
                .task(request.getTask())
                .completed(request.isCompleted())
                .date(date)
                .build();

        Todo saved = todoRepository.save(todo);
        return toDto(saved);
    }

    // Todo 삭제
    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다.");
        }
        todoRepository.deleteById(id);
    }

    // Todo 수정
    public TodoResponseDto update(Long id, TodoRequestDto request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다."));

        todo.setTask(request.getTask());
        todo.setCompleted(request.isCompleted());

        if (request.getDate() != null) {
            todo.setDate(request.getDate());
        }

        Todo updated = todoRepository.save(todo);
        return toDto(updated);
    }

    private TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(
                todo.getId(),
                todo.getTask(),
                todo.isCompleted(),
                todo.getDate()
        );
    }
}
