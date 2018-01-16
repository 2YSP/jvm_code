package cn.sp.chapter3;

/**
 * @Author: 2YSP
 * @Description: 代码演示了两点：
 *              1.对象可以在被gc时自我拯救
 *              2.这种自救的机会只能有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 * @Date: Created in 2018/1/16
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOCK = null;

    public void isAlive(){
        System.out.println("yes,i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOCK = this;
    }

    public static void main(String[] args)throws Throwable {
        SAVE_HOCK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOCK = null;
        System.gc();
        //因为finalize方法的优先级较低，暂停0.5秒，等待它
        Thread.sleep(500);
        if (SAVE_HOCK != null){
            SAVE_HOCK.isAlive();
        }else {
            System.out.println("no,i am dead");
        }

        //下面代码完全相同却拯救失败了
        SAVE_HOCK = null;
        System.gc();
        //因为finalize方法的优先级较低，暂停0.5秒，等待它
        Thread.sleep(500);
        if (SAVE_HOCK != null){
            SAVE_HOCK.isAlive();
        }else {
            System.out.println("no,i am dead");
        }
    }
}
