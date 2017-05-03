package periodic.dao.implementation;

import periodic.dao.AbstractJDBCDao;
import periodic.dao.DaoFactory;
import periodic.dao.exceptions.PersistException;
import periodic.entities.Order;
import periodic.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import static periodic.constants.SqlQueries.*;

public class OrderDaoImpl extends AbstractJDBCDao<Order,Integer> {

    @Override
    public String getSelectQuery() {
        return ORDER_SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return ORDER_CREATE_QUERY;
    }

    @Override
    public String getUpdateQury() {
        return ORDER_UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return ORDER_DELETE_QUERY;
    }

    @Override
    public Order create() throws PersistException {

        Order order = new Order();
        return persist(order);
    }

    @Override
    public List<Order> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<Order> result = new LinkedList<>();
        try {
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id_order"));
                order.setIdUser(resultSet.getInt("user_order"));
                order.setState(resultSet.getBoolean("state_order"));
                order.setOrderDate(resultSet.getDate("date_order"));
                result.add(order);
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    public void preparedStatementForInsert(PreparedStatement preparedStatement, Order object) throws PersistException {
        try{
            preparedStatement.setInt(1, object.getId());
            preparedStatement.setInt(2, object.getIdUser());
            preparedStatement.setBoolean(3, object.isState());
            preparedStatement.setDate(4, object.getOrderDate());
        }
        catch (Exception e){
            throw new PersistException(e);
        }
    }

    @Override
    public void preparedStatementForUpdate(PreparedStatement preparedStatement, Order object) throws PersistException {
        try {
            preparedStatement.setBoolean(1, object.isState());
            preparedStatement.setInt(2, object.getId());
        }
        catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public OrderDaoImpl(Connection connection){
        super(connection);
    }
}
