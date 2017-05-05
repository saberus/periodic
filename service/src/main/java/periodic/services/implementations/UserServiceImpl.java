package periodic.services.implementations;


import periodic.dao.exceptions.PersistException;
import periodic.dao.implementations.UserDaoImpl;
import periodic.entities.Book;
import periodic.entities.User;
import periodic.exceptions.ServiceException;
import periodic.services.AbstractService;

import java.util.List;

public class UserServiceImpl extends AbstractService<User, Long>{

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


    public Long create(User user) throws ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = userDao.create(user);
            transaction.commit();
        }
        catch (PersistException e) {
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return id;
    }

    public User getByPK(int key) throws ServiceException {
        User user;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            user = userDao.getByPK(key);
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return user;
    }

    public void update(User user) throws ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            userDao.update(user);
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
            userDao.delete(key);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }
    }

    public List<User> getAll() throws ServiceException {
        List<User> users;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            users = userDao.getAll();
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return users;
    }

    public User getByLoginAndPassword(String login, String password) throws ServiceException{
        User user;
        try {
         session = util.getSession();
         transaction = session.beginTransaction();
         user = userDao.getUserByLoginAndPassword(login, password);
         transaction.commit();
     }
     catch (PersistException e){
         transaction.rollback();
         throw new ServiceException("", e);
     }
     return user;
    }
}
