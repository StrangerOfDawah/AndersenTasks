package main.java.impClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TheClassLoader extends ClassLoader {

    private Map cash = new HashMap();
    private final String[] classPath;

    public TheClassLoader(String[] classPath) {
        this.classPath = classPath;
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve) {
            resolveClass(result);
        }
        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class result = (Class) cash.get(name);
        if (result != null) {
            System.out.println("Класс " + name + " найден в кэше");
            return result;
        }
        File yaUzeZadolbalsaPridumivat = findFile(name.replace('.', '/'), ".class");
        System.out.println("Class " + name + (yaUzeZadolbalsaPridumivat == null ? "" : " found in " + yaUzeZadolbalsaPridumivat));

        if (yaUzeZadolbalsaPridumivat == null) {
            return findSystemClass(name);
        }

        try {
            byte[] classBytes= loadFileAsBytes(yaUzeZadolbalsaPridumivat);
            result= defineClass(name, classBytes, 0,
                    classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(
                    "Cannot load class " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException(
                    "Format of class file incorrect for class "
                            + name + " : " + e);
        }
        cash.put(name,result);
        return result;

    }

    private File findFile(String name, String extension) {

        File yaUzeZadolbalsaPridumivat2;
        for (int i = 0; i < classPath.length; i++) {
            yaUzeZadolbalsaPridumivat2 = new File((new File(classPath[i])).getPath()
                    + File.separatorChar + name.replace('/', File.separatorChar) + extension);

            if (yaUzeZadolbalsaPridumivat2.exists()) {
                return yaUzeZadolbalsaPridumivat2;
            }
        }
        return null;
    }

    protected URL findResource(String name) {
        File yaUzeZadolbalsaPridumivat3= findFile(name, "");
        if (yaUzeZadolbalsaPridumivat3==null)
            return null;
        try {
            return yaUzeZadolbalsaPridumivat3.toURI().toURL(); //без понятия почему toURL  устарел...
        } catch(java.net.MalformedURLException e) {
            return null;
        }
    }

    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result, 0, result.length);
        } finally {
            try {
                f.close();
            } catch (Exception e) {
               //заглушка
            }
        }
        return result;
    }

}
