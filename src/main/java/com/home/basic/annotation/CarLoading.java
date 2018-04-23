package com.home.basic.annotation;

import java.lang.reflect.Field;

/**
 * 我觉得使用注解最大的好处是，在代码的编译器就可以预见性的决定一个类的实例和他的属性。
 * 创建类的过程还是在运行时，这样可以动态的创建类的实例
 * 还有一种适用场景，如果你项目里面的配置文件比较多，那么可以适当的考虑注解，但是注解的调用比较分散，不如配置文件集中。
 */
public class CarLoading {
    public static void annoProcess(CarDemo carDemo) {

        for (Field field : carDemo.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(CarAnnotation.class)) {  //如果存在MyTag标签
                CarAnnotation carAnnotation = field.getAnnotation(CarAnnotation.class);
                carDemo.setCar(new Car(carAnnotation.name(), carAnnotation.price()));
            }
        }
    }

    public static void main(String[] args) {
        CarDemo ann = new CarDemo();
        annoProcess(ann);
        System.out.println(ann.getCar());
    }

    public static class CarDemo {

        @CarAnnotation(name = "audi", price = 500000)
        private Car car;

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        @Override
        public String toString() {
            return "Annotation [car=" + car + "]";
        }
    }
}
