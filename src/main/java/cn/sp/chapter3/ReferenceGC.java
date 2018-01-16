package cn.sp.chapter3;

/**
 * @Author: 2YSP
 * @Description: 引用计数算法的缺陷
 * @Date: Created in 2018/1/16
 */
public class ReferenceGC {

    public Object instance = null;

    private static final int _1M = 1024 * 1024;

    /**
     * 唯一意义就是占点内存，以便在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1M];

    public static void testGC(){
        ReferenceGC objA = new ReferenceGC();
        ReferenceGC objB = new ReferenceGC();
        //相互引用
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //假设在这里发生GC,那么objA和objB是否能被回收
        System.gc();

    }

    /**
     * -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        //发生了gc
        testGC();
    }
}
