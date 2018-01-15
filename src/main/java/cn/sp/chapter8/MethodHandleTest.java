package cn.sp.chapter8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * MethodHandle 演示
 * Reflection是重量级的，MethodHandle是轻量级的
 * Created by 2YSP on 2018/1/7.
 */
public class MethodHandleTest {

    static class ClassA{
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args)throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        System.out.println("class========="+obj.getClass());
        /******无论obj最终是哪个实现类，下面这句都能正确调用到println方法*******/
        getPrintMH(obj).invokeExact("hello");
    }

    private static MethodHandle getPrintMH(Object receiver)throws Throwable{
        //参数1：返回类型，后面参数代表方法的参数类型
        MethodType mt = MethodType.methodType(void.class, String.class);
        /**
         * lookup()方法：在指定类中查找符合给定的方法名称、方法类型，并且符合调用权限的方法句柄
         */
        return lookup().findVirtual(receiver.getClass(),"println",mt).bindTo(receiver);

    }
}
