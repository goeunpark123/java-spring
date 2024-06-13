package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class BidirectionTests {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    /*설명.
    *  jpql의 기본 문법
    *   1. SELECT, UPDATE, DELETE 등의 키워드 사용은 SQL과 동일
    *   2. INSERT는 persist() 메소드를 사용하면 된다.
    *   3. 키워드는 대소문자를 구분하지 않지만, 엔티티와 속성은 대소문자를 구분하므로 유의한다.
    *   4. 엔티티 별칭을 필수로 사용해야 하며 별칭 없이 작성하면 에러가 발생한다.
    * */

    /*설명.
    *  jpql의 사용 방법은 다음과 같다.
    *   1. 작성한 jpql(문자열)을 'entityManager.createQuery()' 메소드를 통해 쿼리 객체로 만든다.
    *   2. 쿼리 객체는 'TypedQuery', 'Query' 두 가지가 있다.
    *     2-1. TypedQuery: 반환할 타입을 명확하게 지정하는 방식일 때 사용하며 쿼리 객체의 메소드 실행 결과로 지정한 타입잉 반환
    *     2-2. Query: 반환할 타입을 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로 Object 또는 Object[]이 반환된다.
    *   3. 쿼리 객체에서 제공하는 메소드를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.
    *     3-1. getSingleResult(): 결과가 정확히 한 행일 경우 사용하며 없거나 많으면 예외가 발생한다.
    *     3-2. getResultList(): 결과가 2항 이상일 경우 사용하며 컬렉션을 반환한다. 없으면 빈 컬렉션을 반환한다.*/

    /*설명. 양방향 연관관계는 지양되는 방식이며, 순환 참조(특히 toString() 작성 시 조심해야 한다.)*/
    @Test
    public void 양방향_연관관계_매핑_조회_테스트() {
        int menuCode = 10;
        int categoryCode = 10;

        /*설명. 연관관계의 주인인 엔티티는 한 번에 join문으로 관계 맺은 엔티티를 조회해온다.*/
//        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        /*설명. 양방향은 부모에 해당하는 엔티티는 가짜 연관관계이고 필요 시 연관 관계 엔티티를 조회하는 쿼리를 다시 실행하게 된다.*/
        Category foundCategory = entityManager.find(Category.class, categoryCode);

        /*설명. getMenuList() 시점에야 관계를 맺은 Menu 엔티티가 필요해서 select 문이 날아간다.(일종의 지연 로딩 같은 개념)*/
        foundCategory.getMenuList().forEach(System.out::println);
    }
}
