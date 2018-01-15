package cn.sp.chapter2;

/**
 * @Author: 2YSP
 * @Description: 创建线程导致内存溢出异常
 * @Date: Created in 2018/1/15
 */
public class JavaVMStackOOM {

    private void dontStop(){
        while (true){

        }
    }

    private void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(()->{
                dontStop();
            });

            thread.start();
        }
    }

    /**
     * VM Args:-Xss2M
     * @param args
     */
    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
