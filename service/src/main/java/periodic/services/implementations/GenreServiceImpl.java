package periodic.services.implementations;

import periodic.dao.exceptions.PersistException;
import periodic.dao.implementations.GenreDaoImpl;
import periodic.entities.Book;
import periodic.entities.Genre;
import periodic.exceptions.ServiceException;
import periodic.services.AbstractService;

import java.util.List;

public class GenreServiceImpl extends AbstractService<Genre, Long>{

    private static volatile GenreServiceImpl instance;
    private GenreDaoImpl genreDao = GenreDaoImpl.getInstance();

    private GenreServiceImpl(){}

    public static GenreServiceImpl getInstance(){
        if (instance == null){
            synchronized (GenreServiceImpl.class){
                if (instance == null){
                    instance = new GenreServiceImpl();
                }
            }
        }
        return instance;
    }

    public Long create(Genre genre) throws ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = genreDao.create(genre);
            transaction.commit();
        }
        catch (PersistException e) {
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return id;
    }

    public Genre getByPK(int key) throws ServiceException {
        Genre genre;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            genre = genreDao.getByPK(key);
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return genre;
    }

    public void update(Genre genre) throws ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            genreDao.update(genre);
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
            genreDao.delete(key);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }
    }

    public List<Genre> getAll() throws ServiceException {
        List<Genre> genres;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            genres = genreDao.getAll();
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return genres;
    }

}
