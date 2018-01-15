package cn.sp.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2018/1/10
 */
public class GenericTypes2 {
//    public static String method(List<String> list){
//        System.out.println("invoke method(List<String> list)");
//        return "";
//    }

    public static int method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
//        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(a+b));
        System.out.println("dad".codePointAt(1));

    }
}
