package hibernatecrud.dao;

import hibernatecrud.dao.daoInterface.AuthorDao;
import hibernatecrud.model.Author;
import hibernatecrud.util.HiberFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AuthorDaoHiberImpl implements AuthorDao {


    @Override
    public Author findById(int id) {
        return HiberFactory.getSessionFactory().openSession().get(Author.class, id);
    }

    @Override
    public void insert(Author author) {
        Session session = HiberFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(author);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Author author) {

        Session session = HiberFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(author);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(int id) {

        Session session = HiberFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }


}
