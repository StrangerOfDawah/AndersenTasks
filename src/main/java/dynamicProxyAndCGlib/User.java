package dynamicProxyAndCGlib;


public class User implements main.java.dynamicProxyAndCGlib.IUser {

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
