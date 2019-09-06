package hibernatecrud.dao.daoInterface;

import hibernatecrud.model.Author;

public interface AuthorDao {
    Author findById(int id);
    void insert(Author author);
    void update(Author author);
    void delete(int id);
}
