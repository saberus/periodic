package periodic.dao.implementation;

import periodic.connection.ConnectionPool;
import periodic.dao.DaoFactory;
import periodic.dao.GenericDao;
import periodic.dao.exceptions.PersistException;
import periodic.entities.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoFactoryImpl implements DaoFactory<Connection> {

    private Map<Class, DaoCreator> creators;

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public DaoFactoryImpl() {

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Book.class, new DaoCreator<Connection>(){

            @Override
            public GenericDao create(Connection connection) {
                return new BookDaoImpl(connection);}
        });



    }
}
