package periodic.services.implementations;

import periodic.dao.exceptions.PersistException;
import periodic.dao.implementations.RoleDaoImpl;
import periodic.entities.Book;
import periodic.entities.Role;
import periodic.exceptions.ServiceException;
import periodic.services.AbstractService;

import java.util.List;

public class RoleServiceImpl extends AbstractService<Role, Long> {

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


    public Long create(Role role) throws ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = roleDao.create(role);
            transaction.commit();
        }
        catch (PersistException e) {
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return id;
    }

    public Role getByPK(int key) throws ServiceException {
        Role role;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            role = roleDao.getByPK(key);
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return role;
    }

    public void update(Role role) throws ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            roleDao.update(role);
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
            roleDao.delete(key);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }
    }

    public List<Role> getAll() throws ServiceException {
        List<Role> roles;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            roles = roleDao.getAll();
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return roles;
    }
}
