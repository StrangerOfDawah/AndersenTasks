package hibernatecrud.dao.daoInterface;

import hibernatecrud.model.Book;

public interface BookDao {
    Book findById(int id);
    void insert(Book author);
    void update(Book author);
    void delete(int id);
}
