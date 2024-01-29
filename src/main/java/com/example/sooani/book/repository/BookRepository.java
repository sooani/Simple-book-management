package com.example.sooani.book.repository;

import com.example.sooani.book.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findByTitleContains(String title, Pageable pageable);

}//스프링데이터JPA의 기능을 활용하기위해 상속받음, 기본적인 CRUD 가능
//쿼리메소드를 정의하지않아도 메소드 이름 규칙에 따라 자동으로 쿼리 생성

