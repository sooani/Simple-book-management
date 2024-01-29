package com.example.sooani.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false)
    @Positive
    private Integer price;

    @CreationTimestamp
    private LocalDateTime insertDateTime;//날짜 시간 API

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER) //fetch 지연로딩 엔티티가 사용되기 전까지 로딩하지않음
    @Builder.Default
    private List<BookLog> bookLogList = new ArrayList();

}
