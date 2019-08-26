package main.java.dynamicProxyAndCGlib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeInvocationHandler implements InvocationHandler {
    private Object obj;

    public SomeInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getName")) {
            return ((String)method.invoke(obj, args)).toUpperCase();
        }
        System.out.println("OurInvHundler invoke: " + method.getName());
        return method.invoke(obj, args);

    }
}
