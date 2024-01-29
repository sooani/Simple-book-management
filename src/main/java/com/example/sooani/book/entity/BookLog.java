package com.example.sooani.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookLogId;

    @JoinColumn(name = "book_id")//주인이 아닌 클래스에 정의
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private Integer page;

    @CreationTimestamp
    private LocalDateTime insertDateTime;
}
