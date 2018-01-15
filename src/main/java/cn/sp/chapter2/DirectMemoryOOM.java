package cn.sp.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author: 2YSP
 * @Description: 本机直接内存溢出
 * @Date: Created in 2018/1/15
 */
public class DirectMemoryOOM {

    private static final int _1M = 1024 * 1024;

    /**
     *  -Xmx20M -XX:MaxDirectMemorySize=10M
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1M);
        }
    }
}
