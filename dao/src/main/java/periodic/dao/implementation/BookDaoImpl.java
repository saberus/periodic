package periodic.dao.implementation;

import periodic.dao.AbstractJDBCDao;
import periodic.entities.Book;

import java.sql.Connection;

import static periodic.constants.SqlQueries.SELECTUS;


public class BookDaoImpl extends AbstractJDBCDao<Book, Integer> {

    @Override
    public String getSelectQuery() {
        return SELECTUS;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQury() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }
    public BookDaoImpl(Connection connection){
        super(connection);
    }
}
