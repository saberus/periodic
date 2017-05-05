package periodic.services.implementations;

import periodic.dao.implementations.RoleDaoImpl;

public class RoleServiceImpl {

    private static volatile RoleServiceImpl instance;
    private RoleDaoImpl roleDao = RoleDaoImpl.getInstance();

    private RoleServiceImpl(){}

    public static RoleServiceImpl getInstance(){
        if (instance == null){
            synchronized (RoleServiceImpl.class){
                if (instance == null){
                    instance = new RoleServiceImpl();
                }
            }
        }
        return instance;
    }
}
