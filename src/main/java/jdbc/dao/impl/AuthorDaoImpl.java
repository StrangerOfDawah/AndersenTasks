package jdbc.dao.impl;

import jdbc.connecter.Connector;
import jdbc.dao.AuthorDao;
import jdbc.entity.Author;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class AuthorDaoImpl implements AuthorDao {

    public static final String BASE_Q = "select author.idAuthor, author.author_name, book.book_name\n" +
            "  from author\n" +
            "       join author_book on author_book.id_author = author.idAuthor\n" +
            "       join book on book.id_book = author_book.id_book";

    private DataSource dataSource = Connector.getConnection();


    public static void main(String[] args) {
        AuthorDaoImpl imp = new AuthorDaoImpl();
        Author author = new Author();
        author.setId(4);
        imp.delete(author);

    }


    @Override
    public List<Author> findAll() {
        List<Author> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(BASE_Q);
             ResultSet rs = statement.executeQuery();
        ) {

            Map<Integer, Author> authorMap = new HashMap<>();
            while (rs.next()) {
                Integer id = rs.getInt("idAuthor");

                if (!authorMap.containsKey(id)) {
                    Author author = new Author();
                    author.setId(id);
                    author.setName(rs.getString("author_name"));
                    author.setBooks(new ArrayList<>());
                    authorMap.put(id, author);
                }

                authorMap.get(id).getBooks().add(rs.getString("book_name"));
            }
            result.addAll(authorMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public Author findById(Integer id) {
        final String byId = BASE_Q + "       where author.idAuthor = ?";

        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement st = connection.prepareStatement(byId)) {

                Author author = new Author();
                List<String> books = new ArrayList<>();
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException(String.format("Author with ID '%d' is not found", id));
                }

                rs.previous();
                while (rs.next()) {
                    author.setName(rs.getString("author_name"));
                    author.setId(rs.getInt("idAuthor"));
                    books.add(rs.getString("book_name"));
                }

                author.setBooks(books);
                return author;

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    @Override
    public void insert(Author author) {
        final String SQL_INS = "insert into author (author_name, idAuthor) ";
        try (Connection connection = dataSource.getConnection()) {
            try {
                PreparedStatement statement;
                if (author.getId() != null) {
                    statement = connection.prepareStatement(SQL_INS + " values (?, ?) ");
                    statement.setString(1, author.getName());
                    statement.setInt(2, author.getId());
                } else {
                    statement = connection.prepareStatement(SQL_INS + " values (?, NULL)");
                    statement.setString(1, author.getName());
                }
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Author author) {

        final String SQL_BASIC = "update author set author_name = ?" ;
        try (Connection connection = dataSource.getConnection()) {
            try {
                PreparedStatement statement;
                if (author.getId() != null) {
                    statement = connection.prepareStatement(SQL_BASIC + " where idAuthor = ? ");
                    statement.setString(1, author.getName());
                    statement.setInt(2, author.getId());
                    statement.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Author author) {
        final String SQL_DEL = "delete from author where idAuthor = ?";
        try (Connection connection = dataSource.getConnection()) {
            try {
                PreparedStatement statement;
                if (author.getId() != null) {
                    statement = connection.prepareStatement(SQL_DEL);
                    statement.setInt(1, author.getId());
                    statement.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

