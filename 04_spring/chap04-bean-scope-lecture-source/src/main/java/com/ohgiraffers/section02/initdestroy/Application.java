package com.ohgiraffers.section02.initdestroy;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }

        /*설명. 붕어빵, 딸기 우유, 물 빈 객체를 반환 받아 변수에 담는다.*/
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /*설명. 첫번째 손님이 카트에 물건 담는다.*/
        ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);

        cart1.addItem(carpBread);
        cart1.addItem(milk);

        /*설명. 첫번째 손님의 쇼핑 카트에 담긴 물품 확인*/
        System.out.println("cart1에 담긴 물품: " + cart1.getItems());

        /*설명. 두번째 손님이 카트에 물건 담는다.*/
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        /*설명. 두번째 손님의 쇼핑 카트에 담긴 물품 확인*/
        // 같은 객체를 사용
        System.out.println("cart2에 담긴 물품: " + cart2.getItems());
        System.out.println(System.identityHashCode(cart1) == System.identityHashCode(cart2));

        /*설명. main 메소드 종료 전에 의도적으로 container를 소멸(bean도 함께 소멸)*/
        ((AnnotationConfigApplicationContext)context).close();
    }
}
