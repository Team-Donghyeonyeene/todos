package com.example.todolist.service;

import com.example.todolist.dto.MemoRequestDto;
import com.example.todolist.dto.MemoResponseDto;
import com.example.todolist.entity.DailyMemo;
import com.example.todolist.repository.DailyMemoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemoService {

    private final DailyMemoRepository memoRepository;

    public MemoService(DailyMemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 날짜별 메모 조회 (없으면 빈 메모 반환)
    public MemoResponseDto getMemoByDate(LocalDate date) {
        return memoRepository.findByDate(date)
                .map(this::toDto)
                .orElseGet(() -> new MemoResponseDto(null, date, ""));
    }

    // 날짜별 메모 저장 / 수정 (upsert)
    public MemoResponseDto upsertMemo(MemoRequestDto request) {
        LocalDate date = request.getDate();

        DailyMemo memo = memoRepository.findByDate(date)
                .orElseGet(() -> DailyMemo.builder()
                        .date(date)
                        .content("")
                        .build());

        String content = request.getContent();
        memo.setContent(content == null ? "" : content);

        DailyMemo saved = memoRepository.save(memo);

        return toDto(saved);
    }

    private MemoResponseDto toDto(DailyMemo memo) {
        return new MemoResponseDto(
                memo.getId(),
                memo.getDate(),
                memo.getContent()
        );
    }
}
