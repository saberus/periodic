package periodic.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import periodic.dao.exceptions.PersistException;
import periodic.entities.Identified;
import periodic.utils.HibernateUtil;


import java.util.List;

public abstract class AbstractDao<T extends Identified<PK>, PK extends Long> implements IGenericDao<T,PK> {

    protected HibernateUtil util = HibernateUtil.getInstance();
    protected Session session;
    protected Criteria criteria;
    private Class persClass;

    protected AbstractDao(Class clazz){
        persClass = clazz;
    }

    @Override
    public Long create(T object) throws PersistException {
        Long id;
        try {
            session = util.getSession();
            id = (Long) session.save(object);
        }catch (HibernateException e){
            throw new PersistException("", e);
        }
        return id;
    }

    @Override
    public T getByPK(int key) throws PersistException {
        try {

            session = util.getSession();
            return (T) session.get(persClass, key);
        }catch (HibernateException e){
            throw new PersistException("", e);
        }

    }

    @Override
    public void update(T object) throws PersistException {
        try {
            session = util.getSession();
            session.update(object);
        }catch (HibernateException e){
            throw new PersistException("", e);
        }
    }

    @Override
    public void delete(int key) throws PersistException {
        try {
            session = util.getSession();
            T object = getByPK(key);
            session.delete(object);
        }catch (HibernateException e) {
            throw new PersistException("", e);
        }
    }

    @Override
    public List getAll() throws PersistException {
        List<T> result;
        try {
            session = util.getSession();
            criteria = session.createCriteria(persClass);
            result = criteria.list();
            return result;
        }catch (HibernateException e){
            throw new PersistException("", e);
        }

    }
}
