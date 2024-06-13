package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.Account;
import com.ohgiraffers.common.MemberDTO;
import com.ohgiraffers.common.PersonalAccount;
import org.springframework.context.annotation.Bean;

public class ContextConfiguration {

    @Bean
    public Account accountGenerator() {
        return new PersonalAccount(20, "110-234-5678");
    }

    @Bean
    public MemberDTO memberGenerator() {
        MemberDTO member = new MemberDTO();

        /*설명. 메소드에서 반환한 값(Account bean)을 setter 주입*/
//        member.setSequence(1);
//        member.setName("홍길동");
//        member.setPhone("010-1234-5678");
//        member.setEmail("hong123@gmail.com");
//        member.setPersonalAccount(accountGenerator());

        /*설명. 메소드에서 반환한 값을 생성자에 주입*/
//        return member;
        return new MemberDTO(1, "홍길동", "010-1234-5678", "hong123@gmail.com", accountGenerator());
    }
}
