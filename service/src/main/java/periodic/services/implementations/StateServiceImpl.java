package periodic.services.implementations;

import periodic.dao.exceptions.PersistException;
import periodic.dao.implementations.StateDaoImpl;
import periodic.entities.Book;
import periodic.entities.State;
import periodic.exceptions.ServiceException;
import periodic.services.AbstractService;

import java.util.List;

public class StateServiceImpl extends AbstractService<State, Long> {

    private static volatile StateServiceImpl instance;
    private StateDaoImpl stateDao = StateDaoImpl.getInstance();

    private StateServiceImpl(){}

    public static StateServiceImpl getInstance(){
        if (instance == null){
            synchronized (StateServiceImpl.class){
                if (instance == null){
                    instance = new StateServiceImpl();
                }
            }
        }
        return instance;
    }


    public Long create(State state) throws ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = stateDao.create(state);
            transaction.commit();
        }
        catch (PersistException e) {
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return id;
    }

    public State getByPK(int key) throws ServiceException {
        State state;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            state = stateDao.getByPK(key);
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return state;
    }

    public void update(State state) throws ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            stateDao.update(state);
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
            stateDao.delete(key);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }

    }

    public List<State> getAll() throws ServiceException {
        List<State> states;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            states = stateDao.getAll();
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return states;
    }
}
