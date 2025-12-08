package com.example.todolist.repository;

import com.example.todolist.entity.DailyMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyMemoRepository extends JpaRepository<DailyMemo, Long> {

    Optional<DailyMemo> findByDate(LocalDate date);
}
