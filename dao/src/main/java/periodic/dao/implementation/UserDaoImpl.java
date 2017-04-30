package periodic.dao.implementation;

import periodic.dao.AbstractJDBCDao;
import periodic.dao.exceptions.PersistException;
import periodic.entities.User;
import periodic.entities.enums.ERolesUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import static periodic.constants.SqlQueries.*;

public class UserDaoImpl extends AbstractJDBCDao<User, Integer>{

    @Override
    public String getSelectQuery() {
        return USER_SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return USER_CREATE_QUERY;
    }

    @Override
    public String getUpdateQury() {
        return USER_UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return USER_DELETE_QUERY;
    }

    @Override
    public User create() throws PersistException {
        return null;
    }

    @Override
    public List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("login_user"));
                user.setPassword(resultSet.getString("password_user"));
                user.setEmail(resultSet.getString("email_user"));
                user.setRole(ERolesUser.valueOf(resultSet.getString("role_user")));
                result.add(user);
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public void preparedStatementForInsert(PreparedStatement preparedStatement, User object) throws PersistException {
        try {
            preparedStatement.setString(1, object.getLogin());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setString(3, object.getEmail());
            preparedStatement.setString(4, object.getRole().toString());
        }
        catch (Exception e){
            throw new PersistException(e);
        }
    }

    @Override
    public void preparedStatementForUpdate(PreparedStatement preparedStatement, User object) throws PersistException {
        try {
            preparedStatement.setString(1, object.getLogin());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setString(3, object.getEmail());
            preparedStatement.setString(4, object.getRole().toString());
            preparedStatement.setInt(5, object.getId());
        }
        catch (Exception e){
            throw new PersistException(e);
        }
    }

    public UserDaoImpl(Connection connection){
        super(connection);
    }
}
