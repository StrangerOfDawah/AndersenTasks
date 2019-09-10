package hibernatecrud;

import hibernatecrud.dao.AuthorDaoHiberImpl;
import hibernatecrud.dao.daoInterface.AuthorDao;
import hibernatecrud.model.Author;
import hibernatecrud.model.Book;

import java.util.HashSet;
import java.util.Set;

public class AppDemo {
    public static void main(String[] args) {
        AuthorDao authorDao = new AuthorDaoHiberImpl();
//        Author author = new Author();
//        author.setName("Tolstoy2");
//        Book book = new Book();
//        book.setName("Detstvo2");
//        Set<Book> hz = new HashSet<>();
//        hz.add(book);
//        author.setBooks(hz);

        Author author = new Author();
        author.setId(16);
        authorDao.delete(author);
    }
}
