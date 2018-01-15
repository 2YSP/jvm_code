package cn.sp.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 2YSP
 * @Description: 运行时常量池导致的内存溢出
 * @Date: Created in 2018/1/15
 */
public class RuntimeConstantPoolOOM {

    /**
     * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
     * @param args
     */
    public static void main(String[] args) {
        //使用list保持常量池引用，避免Full GC 回收常量池行为
        List<String> list =new ArrayList<String>();

        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
