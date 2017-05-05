package periodic.dao.implementations;

import periodic.dao.AbstractDao;
import periodic.entities.Role;

public class RoleDaoImpl extends AbstractDao<Role, Long> {

    private static volatile RoleDaoImpl instance;

    private RoleDaoImpl(){
        super(Role.class);
    }

    public static RoleDaoImpl getInstance(){
        if (instance == null){
            synchronized (RoleDaoImpl.class){
                if (instance == null){
                    instance = new RoleDaoImpl();
                }
            }
        }
        return instance;
    }
}
