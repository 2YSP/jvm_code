package cn.sp.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 2YSP
 * @Description: 当泛型遇见重载
 * @Date: Created in 2018/1/10
 */
public class GenericTypes {

    //两个方法的特征签名相同，编译报错
//    public static void method(List<String> list){
//        System.out.println("invoke method(List<String> list)");
//    }

    public static void method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list)");
    }

}
