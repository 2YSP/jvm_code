package cn.sp.chapter8;

/**
 * 单分派和多分派演示
 * Created by 2YSP on 2018/1/7.
 */
public class Dispatch {

    static class QQ{

    }

    static class _360{

    }

    public static class Father{
        public void hardChoice(QQ arg){
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg){
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father{

        @Override
        public void hardChoice(QQ arg){
            System.out.println("son choose qq");
        }

        @Override
        public void hardChoice(_360 arg){
            System.out.println("son choose 360");
        }
    }

    /**
     * java语言的静态分派属于多分派
     *          动态分派属于单分派
     * @param args
     */
    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());//father choose 360
        son.hardChoice(new QQ());//son choose qq
    }
}
