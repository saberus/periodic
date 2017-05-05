package periodic.dao.implementations;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import periodic.dao.AbstractDao;
import periodic.dao.exceptions.PersistException;
import periodic.entities.Order;
import periodic.utils.HibernateUtil;


public class OrderDaoImpl extends AbstractDao<Order,Long> {

    private static volatile OrderDaoImpl instance;

    private OrderDaoImpl(){
        super(Order.class);
    }

    public static OrderDaoImpl getInstance(){
        if (instance == null){
            synchronized (OrderDaoImpl.class){
                if (instance == null){
                    instance = new OrderDaoImpl();
                }
            }
        }
        return instance;
    }

    public int getOrdersCount()throws PersistException{
        try {
            session = HibernateUtil.getInstance().getSession();
            return Integer.parseInt(session.createCriteria(Order.class)
            .setProjection(Projections.rowCount())
            .uniqueResult().toString());
        }
        catch (HibernateException e){
            throw new PersistException("", e);
        }
    }

}
