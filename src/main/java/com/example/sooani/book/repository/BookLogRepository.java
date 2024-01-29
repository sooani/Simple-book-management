package com.example.sooani.book.repository;

import com.example.sooani.book.entity.BookLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLogRepository extends JpaRepository<BookLog, Integer> {
}//책 기록용 리포지터리
