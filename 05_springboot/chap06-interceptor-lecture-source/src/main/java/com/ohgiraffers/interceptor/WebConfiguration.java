package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*설명. Interceptor 추가 및 static(정적) 리소스 호출 경로 등록 설정*/
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private StopwatchInterceptor stopwatchInterceptor;

    @Autowired
    public WebConfiguration(StopwatchInterceptor stopwatchInterceptor) {
        this.stopwatchInterceptor = stopwatchInterceptor;
    }

    /*설명. interceptor가 실제로 동작하기 위해서는 따로 여기서 등록하는 과정이 필요*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stopwatchInterceptor);
        //interceptor는 핸들러 메소드 앞뒤로 실행
//                .excludePathPatterns("/css/**");              //excludePathPatterns로 등록한 경로의 요청은 인터셉터가 가로채지 않음
    }
}
