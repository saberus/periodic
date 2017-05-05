package periodic.dao.implementations;

import periodic.dao.AbstractDao;

import periodic.entities.User;

public class UserDaoImpl extends AbstractDao<User, Long> {

    private static UserDaoImpl instance;

    private UserDaoImpl() {
        super(User.class);
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

}
