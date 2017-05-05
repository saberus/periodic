package periodic.services.implementations;

import periodic.dao.exceptions.PersistException;
import periodic.dao.implementations.OrderDaoImpl;
import periodic.entities.Book;
import periodic.entities.Order;
import periodic.exceptions.ServiceException;
import periodic.services.AbstractService;

import java.util.List;

public class OrderServiceImpl extends AbstractService<Order, Long> {

    private static volatile OrderServiceImpl instance;
    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    private OrderServiceImpl(){}

    public static OrderServiceImpl getInstance(){
        if (instance == null){
            synchronized (OrderDaoImpl.class){
                if (instance == null){
                    instance = new OrderServiceImpl();
                }
            }
        }
        return instance;
    }


    public Long create(Order order) throws ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = orderDao.create(order);
            transaction.commit();
        }
        catch (PersistException e) {
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return id;
    }

    public Order getByPK(int key) throws ServiceException {
        Order order;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            order = orderDao.getByPK(key);
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return order;
    }

    public void update(Order order) throws ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            orderDao.update(order);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }
    }

    public void delete(int key) throws ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            orderDao.delete(key);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }

    }

    public List<Order> getAll() throws ServiceException {
        List<Order> orders;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            orders = orderDao.getAll();
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return orders;
    }

    public int getOrderCount() throws ServiceException {
        int orderCount;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            orderCount = orderDao.getOrdersCount();
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }
        return orderCount;
    }
}
