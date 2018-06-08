package com.home.basic.sharding;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MainApp.class);
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        System.out.println("所以beanNames个数：" + beanNames.length);
//        for (String bn : beanNames) {
//            System.out.println(bn);
//        }
    }

    @Bean
    public User userBean() {
        User user = new User();
        user.username = "xxu";
        return user;
    }
}

class User {
    String username;
}

@Component
class UserService {
    @Autowired
    private User userBean;

//    @PostConstruct
    public void echoUser() {
        System.out.println(userBean.username);
//        new Thread(() -> {
//            while (true) {
//                System.out.println(123);
//            }
//        }).start();
    }
}

class ApplicationEventListener implements ApplicationListener {

   @Override
   public void onApplicationEvent(ApplicationEvent event) {
       // 在这里可以监听到Spring Boot的生命周期
       if (event instanceof ApplicationEnvironmentPreparedEvent) { //
           System.out.println("初始化环境变量 ");
       } else if (event instanceof ApplicationPreparedEvent) { // 初始化完成
           System.out.println("初始化完成  ");
       } else if (event instanceof ContextRefreshedEvent) { // 应用刷新
           System.out.println("应用刷新   ");
       } else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
           System.out.println("应用已启动完成    ");
           User userBean = (User) ((ApplicationReadyEvent) event).getApplicationContext().getBean("userBean");
           System.out.println(userBean.username);
       } else if (event instanceof ContextStartedEvent) { // 应用启动，需要在代码动态添加监听器才可捕获
           System.out.println("应用启动，需要在代码动态添加监听器才可捕获    ");
       } else if (event instanceof ContextStoppedEvent) { // 应用停止
           System.out.println("应用停止    ");
       } else if (event instanceof ContextClosedEvent) { // 应用关闭
           System.out.println("应用关闭     ");
       }
   }

}
