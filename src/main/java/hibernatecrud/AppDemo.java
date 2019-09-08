package hibernatecrud;

import hibernatecrud.dao.AuthorDaoHiberImpl;
import hibernatecrud.dao.daoInterface.AuthorDao;

public class AppDemo {
    public static void main(String[] args) {
        AuthorDao authorDao = new AuthorDaoHiberImpl();
        System.out.println(authorDao.findById(8));
    }
}
