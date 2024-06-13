package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service        // bean으로 인식
public class BookService {

    /*설명. ComponentScan 범위 안에 어노테이션들이 달렸을 때 유효
    *  1. @Service에 의해서 BookService 타입의 bookService bean이 관리
    *  2. BookDAOImpl에 있는 @Repository에 의해서 bookDAOImpl bean이 관리(bookDAO 타입이기도 한)
    *  3. @Autowired에 의해서 BookDAO 타입의 bean이 BookService의 필드인 bookDAO 필드에 주입(대입)*/

    @Autowired
    // (@Repository가 있는 클래스) private BookDAO bookDAO = new BookDAOImpl(); 자식 클래스 많으면 알파벳 순?으로 첫번째
    private BookDAO bookDAO;            // ComponentScan 범위 안 BookDAO 타입의 bean이 대입

    /*설명. 도서 목록 전체 조회*/
    public List<BookDTO> findAllBook() {
        return bookDAO.findAllBook();
    }

    public BookDTO searchBookBySequence(int sequence) {
        return bookDAO.searchBookBySequence(sequence);
    }
}
