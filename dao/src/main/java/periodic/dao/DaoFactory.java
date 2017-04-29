package periodic.dao;


import periodic.connection.ConnectionPool;
import periodic.dao.exceptions.PersistException;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory<Context> {

    public interface DaoCreator<Context>{
        public GenericDao create(Context context);
    }

    public GenericDao getDao(Context context, Class dtoClass) throws PersistException;

}
