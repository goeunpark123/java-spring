package org.example.springdatajpa.common;

import org.springframework.data.domain.Page;

public class Pagination {

    /*설명. PagingButtonInfo를 생성해서(버튼 생성에 필요한 정보를 생성) 반환하는 static 메소드*/
    public static PagingButtonInfo getPagingButtonInfo(Page page) {

        int currentPage = page.getNumber() + 1;     // 인덱스 개념 -> 실제 페이지 번호의 개념으로 다시 변경
        int defaultButtonCount = 10;                // 한 페이지에 보일 버튼 최대 갯수
        int startPage;                              // 한 페이지에 보여질 첫 버튼
        int endPage;                                // 한 페이지에 보여질 마지막 버튼

        startPage = (int)(Math.ceil((double)currentPage / defaultButtonCount) - 1) * defaultButtonCount + 1;
        endPage = startPage + defaultButtonCount - 1;

        /*설명. 예외 상황 처리*/
        if (page.getTotalPages() < endPage)         // 총 페이지 수가 보여질 마지막 버튼보다 작으면
            endPage = page.getTotalPages();         // 곧 총 페이지 수가 마지막 버튼이 된다.

        if (page.getTotalPages() == 0) {
            endPage = startPage;
        }

        return new PagingButtonInfo(currentPage, startPage, endPage);
    }
}
