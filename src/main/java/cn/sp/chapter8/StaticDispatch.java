package cn.sp.chapter8;

/**
 * @Author: 2YSP
 * @Description: 方法静态分派演示
 * @Date: Created in 2018/1/5
 */
public class StaticDispatch {
    static abstract class Human{

    }

    static class Man extends Human{

    }

    static class Woman extends Human{

    }

    public void sayHello(Human guy){
        System.out.println("hello,gay");
    }

    public void sayHello(Man guy){
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy){
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);// hello,gay
        sd.sayHello(woman);// hello,gay

    }
}
