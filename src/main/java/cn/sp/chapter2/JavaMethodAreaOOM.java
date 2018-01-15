package cn.sp.chapter2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: 2YSP
 * @Description: 借助CGlib使得方法区出现内存溢出异常
 * @Date: Created in 2018/1/15
 */
public class JavaMethodAreaOOM {

    /**
     * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
     * @param args
     */
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(obj,args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{

    }
}
