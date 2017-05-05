package periodic.dao.implementations;

import periodic.dao.AbstractDao;
import periodic.entities.Order;


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

}
