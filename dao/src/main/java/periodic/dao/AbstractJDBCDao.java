package periodic.dao;

import periodic.dao.exceptions.PersistException;
import sun.security.util.PendingException;

import javax.swing.text.html.HTMLDocument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 *
 */

public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T,PK>{

    private Connection connection;

    /**
     * Возвращает sql запрос для получения всех записей
     */
    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQury();

    public abstract String getDeleteQuery();

    public abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;

    public abstract void preparedStatementForInsert(PreparedStatement preparedStatement, T object)throws PersistException;

    public abstract void preparedStatementForUpdate(PreparedStatement preparedStatement, T object) throws PersistException;

    public T persist(T object) throws PersistException {
        T persistInstance;
        //Добавляем запись
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            preparedStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1){
                throw new PersistException("On persist modify more then one record" + count);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        //Получаем только что вставленную запись
        sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            List<T> list = parseResultSet(resultSet);
            if ((list == null) || (list.size()) != 1){
                throw new PersistException("Exception on finding more then one record on PK");
            }
            persistInstance = list.iterator().next();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }

    public T getByPK(int key) throws PersistException{
        List<T> list;
        String sql = getSelectQuery();
        sql += "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        if(list == null || list.size() == 0) {
            throw new PersistException("Record with PK" + key + "not found");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more then one record on this PK: " + key);
        }
        return list.iterator().next();
    }

    public List<T> getAll()throws PersistException{
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        }catch (Exception e){
            throw new PersistException(e);
        }
/*        if (list == null || list.size() == 0){
            //TODO
        }*/
        return list;
    }

    public void update(T object) throws PersistException{
        String sql = getUpdateQury();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            preparedStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1){
                throw new PersistException("On update modify more than one record: " + count);
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }

    }

    public void delete(T object) throws PersistException{
        String sql = getDeleteQuery();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            try {
                statement.setObject(1, object.getId());
            }catch (Exception e){
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1){
                throw new PersistException("On delete modify more then one recod: " + count);
            }
            statement.close();
        }catch (Exception e){
            throw new PersistException(e);
        }

    }


    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

}
