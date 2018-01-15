package cn.sp.chapter8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 *
 * 方法调用问题
 * Created by 2YSP on 2018/1/7.
 */
public class Test {
    class GrandFather{
        void thinking(){
            System.out.println("I am a grandfather");
        }
    }

    class Father extends GrandFather{
        @Override
        void thinking() {
            System.out.println("I am a father");
        }
    }

    class Son extends Father{
        @Override
        void thinking() {
            //在这里填入适当的代码（不能修改其他地方的代码）
            //实现调用祖父的thinking()方法,打印"I am a grandfather"
            //jdk1.7后使用MethodHandle解决问题
            try {
                MethodType mt = MethodType.methodType(void.class);
                /**按书上的代码输出结果却是：I am a father,可能因为我用的为Jdk1.8版本,做了一些改动**/
//                MethodHandle mh = lookup().findSpecial(GrandFather.class,"thinking",mt,getClass());
//                mh.invoke(this);
                MethodHandle mh = lookup().findVirtual(GrandFather.class, "thinking", mt).bindTo(new GrandFather());
                mh.invokeExact();
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new Test().new Son()).thinking();
    }

}
