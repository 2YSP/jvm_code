package cn.sp.chapter8;

/**
 * @Author: 2YSP
 * @Description: 动态分派
 * @Date: Created in 2018/1/5
 */
public class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human{

        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human{

        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    /**
     * 输出
     *   man say hello
         woman say hello
         woman say hello
     *
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
