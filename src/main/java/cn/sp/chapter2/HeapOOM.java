package cn.sp.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 2YSP
 * @Description: java堆内存溢出异常测试
 * @Date: Created in 2018/1/15
 */
public class HeapOOM {

    static class OOMObject{

    }

    /**
     * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\file
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
