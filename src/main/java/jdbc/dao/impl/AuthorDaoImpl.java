package jdbc.dao.impl;

import jdbc.connecter.Connector;
import jdbc.dao.AuthorDao;
import jdbc.entity.Author;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class AuthorDaoImpl implements AuthorDao {

    public static final String BASE_Q = "" +
            "select author.idAuthor, author.author_name, book.book_name\n" +
            "  from author\n" +
            "       join author_book on author_book.id_author = author.idAuthor\n" +
            "       join book on book.id_book = author_book.id_book";

    private DataSource dataSource = Connector.getConnection();


    @Override
    public List<Author> findAll() {
        List<Author> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(BASE_Q);
             ResultSet rs = statement.executeQuery();
        ) {

            Map<Integer, Author> authorMap = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt("idAuthor");

                if (!authorMap.containsKey(id)) {
                    Author author = new Author();
                    author.setId(id);
                    author.setName(rs.getString("author_name"));
                    author.setBooks(new ArrayList<String>());
                    authorMap.put(id, author);
                }

                authorMap.get(id).getBooks().add(rs.getString("book_name"));
            }
            result.addAll(authorMap.values());

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return result;
    }


    @Override
    public Author findById(int id) {
        final String byId = BASE_Q + " where author.idAuthor = ?";

        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement st = connection.prepareStatement(byId)) {

                st.setInt(1, id);

                try (ResultSet rs = st.executeQuery()) {

                    Author author = new Author();
                    List<String> books = new ArrayList<>();

                    if (!rs.next()) {
                        throw new RuntimeException(String.format("Author with ID '%d' is not found", id));
                    }

                    author.setName(rs.getString("author_name"));
                    author.setId(rs.getInt("idAuthor"));
                    while (rs.next()) {
                        books.add(rs.getString("book_name"));
                    }
                    author.setBooks(books);
                    return author;

                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    @Override
    public void insert(Author author) {
        final String SQL_INS = "insert into author (author_name) ";
        try (Connection connection = dataSource.getConnection()) {
            try {
                PreparedStatement statement;
                if (author.getId() != null) {
                    throw new RuntimeException("A record with this id already exists");
                } else {
                    statement = connection.prepareStatement(SQL_INS + " values (?) ");
                    statement.setString(1, author.getName());
                }
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    @Override
    public void update(Author author) {

        final String SQL_UPD = "update author set author_name = ? where idAuthor = " + author.getId();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_UPD)) {

                if (author.getId() != null) {
                    statement.setString(1, author.getName());
                    statement.executeUpdate();
                } else {
                    throw new RuntimeException("You did not add an id");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public static void main(String[] args) {
        Author author = new Author();
        author.setId(1);
        AuthorDaoImpl a = new AuthorDaoImpl();
        a.delete(author.getId());
    }

    @Override
    public void delete(int id) {
        final String deleteLink = "delete from author_book where id_author =  " + id;

        final String deleteBooks = "delete from book " +
                "where not exists (select id_book from author_book " +
                "where author_book.id_book = book.id_book)";

        final String deleteAuthors = "delete from author " +
                "where not exists (select id_author from author_book " +
                "where author_book.id_author = author.idAuthor)";


        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);
            deleteAction(connection, deleteLink);
            deleteAction(connection, deleteBooks);
            deleteAction(connection, deleteAuthors);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }


    private void deleteAction(Connection connection, String st) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(st)) {
            statement.executeUpdate();

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}


