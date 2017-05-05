package periodic.services.implementations;


import periodic.dao.implementations.UserDaoImpl;

public class UserServiceImpl {

    private static volatile UserServiceImpl instance;
    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance(){
        if(instance == null){
            synchronized (UserServiceImpl.class){
                if (instance == null){
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

}
