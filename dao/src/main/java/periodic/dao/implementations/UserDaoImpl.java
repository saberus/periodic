package periodic.dao.implementations;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import periodic.dao.AbstractDao;

import periodic.dao.exceptions.PersistException;
import periodic.entities.User;
import periodic.utils.HibernateUtil;

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

    public User getUserByLoginAndPassword(String login, String password) throws PersistException{
        try{
            session = HibernateUtil.getInstance().getSession();
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.like("user_login", login))
                    .add(Restrictions.like("user_password", password))
                    .uniqueResult();

        }catch (HibernateException e){
            throw new PersistException("", e);
        }
    }

}
