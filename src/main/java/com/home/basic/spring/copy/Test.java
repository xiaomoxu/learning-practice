package com.home.basic.spring.copy;


public class Test {
    public static void main(String[] args) {
        ClassPathXMLApplicationContext context = new ClassPathXMLApplicationContext("application.xml");
        StudentService stuServ = (StudentService) context.getBean("StudentService");
        stuServ.getStudent().selfIntroDuction();
    }

}