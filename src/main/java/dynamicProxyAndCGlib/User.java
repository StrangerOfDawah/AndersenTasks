package main.java.dynamicProxyAndCGlib;

public class User implements IUser {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void reName(String newName) {
        if(!newName.equals(name)){
            this.name = name;
        }
    }
}
