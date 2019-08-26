/*
package main.java.impClassLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Demo {

    public static void main(String[] args) throws Exception {

        ClassLoader loader;
        while (true) {
            loader = new TheClassLoader(new String[] {"."});
            Class clazz = Class.forName("TestDownloadingClass", true, loader);

            Object obj = clazz.getDeclaredConstructor().newInstance();
            System.out.println(obj);
            new BufferedReader(new InputStreamReader(System.in)).readLine();

        }
    }

}
*/
