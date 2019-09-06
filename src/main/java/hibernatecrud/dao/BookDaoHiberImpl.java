package hibernatecrud.dao;

import hibernatecrud.dao.daoInterface.BookDao;
import hibernatecrud.model.Book;
import hibernatecrud.util.HiberFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookDaoHiberImpl implements BookDao {


    @Override
    public Book findById(int id) {
        return HiberFactory.getSessionFactory().openSession().get(Book.class, id);
    }

    @Override
    public void insert(Book book) {
        Session session = HiberFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Book book) {

        Session session = HiberFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
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
