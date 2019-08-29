package jdbc.dao;

import jdbc.entity.Author;

import java.util.List;

public interface AuthorDao {

    public List<Author> findAll();
    public Author findById(Integer id);
    public void insert(Author author);
    public void update(Author author);
    public void delete(Author author);
}
