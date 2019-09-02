package jdbc.dao;

import jdbc.entity.Author;

import java.util.List;

public interface AuthorDao {

     List<Author> findAll();
     Author findById(int id);
     void insert(Author author);
     void update(Author author);
     void delete(int id);
}
