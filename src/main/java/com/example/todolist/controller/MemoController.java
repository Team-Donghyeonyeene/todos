package com.example.todolist.controller;

import com.example.todolist.dto.MemoRequestDto;
import com.example.todolist.dto.MemoResponseDto;
import com.example.todolist.service.MemoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    // 날짜별 메모 조회
    @GetMapping
    public MemoResponseDto getMemo(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return memoService.getMemoByDate(date);
    }

    // 날짜별 메모 저장/수정
    @PutMapping
    public MemoResponseDto saveMemo(@Valid @RequestBody MemoRequestDto request) {
        return memoService.upsertMemo(request);
    }
}
