package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import com.ohgiraffers.common.MemberDAO;
import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*설명. 설정용 클래스도 bean으로 관리되며 이름(bean의 id) 부여 가능*/
@Configuration("configurationSection03")

/*설명 1. 단순히 범위 지정만으로 @Component 계열의 어노테이션을 모두 bean으로 관리*/
// basePackages로 범위를 지정 (디폴트로는 자신이 포함된 패키지만 scan)
//@ComponentScan(basePackages = "com.ohgiraffers")    // scan해서 어노테이션 달려있으면 빈으로 관리

/*설명 2. 범위 및 필터를 적용해서 bean을 관리(excludeFilters)*/
// 이름 명시 안하면 클래스 이름이 디폴트 bean의 id로 부여
//@ComponentScan(basePackages = "com.ohgiraffers",
//               excludeFilters = {
//                   @ComponentScan.Filter(
//                       /*설명 2-1. 타입으로 설정*/
////                       type = FilterType.ASSIGNABLE_TYPE,
////                       classes = {MemberDAO.class}
//
//                       /*설명 2-2. 어노테이션 종류로 설정*/
//                       type = FilterType.ANNOTATION,
//                       classes = {org.springframework.stereotype.Repository.class}
//                   )
//               })

/*설명 3. 범위 및 필터를 적용해서 bean을 관리(includeFilters)*/
@ComponentScan(basePackages = "com.ohgiraffers", //"com",
                useDefaultFilters = false,
                includeFilters = {
                    @ComponentScan.Filter(
                            type=FilterType.ASSIGNABLE_TYPE,
                            classes = {MemberDTO.class}
                    )
                })

/*설명. @Configuration 클래스가 위치한 패키지와 해당 패키지 하위만 scan -> ???
    @ComponentScan의 필터는 상위 범위나 다른 패키지를 scan하기 위해 사용*/
public class ContextConfiguration {

}
