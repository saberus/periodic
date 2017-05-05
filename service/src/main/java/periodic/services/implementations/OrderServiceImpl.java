package periodic.services.implementations;

import periodic.dao.implementations.OrderDaoImpl;

public class OrderServiceImpl {

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
}
