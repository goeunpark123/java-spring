package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Application {
    public static void main(String[] args) {

        /*설명.
        *  i18n 소프트웨어의 국제화(Internationalization에서 I와 n 사이의 18개의 알파벳)
        *  소프트웨어를 국제화하기 위해 처리해야 할 작업
        *   1. 언어, 지역별 번역
        *   2. OS / Platform별 인코딩
        *   3. 문자열 치환 방법
        *   4. 국제화 UI(문자열 크기 변화, 폰트, 아이콘 등)
        *   5. 쓰기 방향의 차이
        *   6. 숫자, 공백, 화폐, 날짜, 주소, 측정 단위 등
        *   7. 타임존, 섬머 타임 등 시각
        *   8. 문자열 정렬 방법*/

        /*설명. 다국어 메세지 처리*/
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String error404MessageKR = context.getMessage("error.404", null, Locale.KOREA);
        String error500MessageKR = context.getMessage("error.500", new Object[]{"여러분", new java.util.Date()}, Locale.KOREA);

        System.out.println("error404MessageKR 메세지 = " + error404MessageKR);
        System.out.println("error500MessageKR 메세지 = " + error500MessageKR);

        String error404MessageUS = context.getMessage("error.404", null, Locale.US);
        String error500MessageUS = context.getMessage("error.500", new Object[]{"you", new java.util.Date()}, Locale.US);

        System.out.println("error404MessageUS message = " + error404MessageUS);
        System.out.println("error500MessageUS message = " + error500MessageUS);
    }
}
