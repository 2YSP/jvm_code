package cn.sp.chapter3;

/**
 * @Author: 2YSP
 * @Description: 新生代Minor GC
 * @Date: Created in 2018/1/16
 */
public class MinorGCTest {

    private static final int _1M = 1024 * 1024;

    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        //出现一次Minor GC
        allocation4 = new byte[4 * _1M];
    }

    /**
     *  VM args: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8
     * @param args
     */
    public static void main(String[] args) {
        testAllocation();
    }
}
