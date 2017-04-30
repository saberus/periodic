package periodic.dao.implementation;

import periodic.dao.AbstractJDBCDao;
import periodic.dao.exceptions.PersistException;
import periodic.entities.Book;
import periodic.entities.enums.EGenreBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import static periodic.constants.SqlQueries.*;


public class BookDaoImpl extends AbstractJDBCDao<Book, Integer> {



    @Override
    public String getSelectQuery() {
        return BOOK_SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return BOOK_CREATE_QUERY;
    }

    @Override
    public String getUpdateQury() {
        return BOOK_UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return BOOK_DELETE_QUERY;
    }

    @Override
    public Book create() throws PersistException {
        Book book = new Book();
        return persist(book);
    }

    @Override
    public List<Book> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<Book> result = new LinkedList<Book>();
        try {
            while (resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getInt("id_book"));
                book.setName(resultSet.getString("name_book"));
                book.setAuthor(resultSet.getString("author_book"));
                book.setGenre(EGenreBook.valueOf(resultSet.getString("genre_book")));
                result.add(book);
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public void preparedStatementForInsert(PreparedStatement preparedStatement, Book object) throws PersistException {
        try{
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAuthor());
            preparedStatement.setString(3,object.getGenre().toString());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void preparedStatementForUpdate(PreparedStatement preparedStatement, Book object) throws PersistException {
        try{
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAuthor());
            preparedStatement.setString(3, object.getGenre().toString());
            preparedStatement.setInt(4, object.getId());
        }
        catch (Exception e){
            throw new PersistException(e);
        }
    }

    public BookDaoImpl(Connection connection){
        super(connection);
    }
}
