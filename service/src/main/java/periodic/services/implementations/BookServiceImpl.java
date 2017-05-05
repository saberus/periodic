package periodic.services.implementations;

import periodic.dao.exceptions.PersistException;
import periodic.dao.implementations.BookDaoImpl;
import periodic.entities.Book;
import periodic.exceptions.ServiceException;
import periodic.services.AbstractService;

import java.util.List;

public class BookServiceImpl extends AbstractService<Book, Long> {

    private static volatile BookServiceImpl instance;
    private BookDaoImpl bookDao = BookDaoImpl.getInstance();

    private BookServiceImpl(){}

    public static BookServiceImpl getInstance(){
        if (instance == null){
            synchronized (BookServiceImpl.class){
                if (instance == null){
                    instance = new BookServiceImpl();
                }
            }
        }
        return instance;
    }


    public Long create(Book book) throws ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = bookDao.create(book);
            transaction.commit();
        }
        catch (PersistException e) {
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return id;
    }

    public Book getByPK(int key) throws ServiceException {
        Book book;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            book = bookDao.getByPK(key);
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return book;
    }

    public void update(Book book) throws ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            bookDao.update(book);
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
            bookDao.delete(key);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }

    }

    public List<Book> getAll() throws ServiceException {
        List<Book> books;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            books = bookDao.getAll();
            transaction.commit();
        }
        catch (PersistException e){
            transaction.rollback();
            throw new ServiceException("", e);
        }
        return books;
    }

    public List<Book> getBooksByPage(int pageNumber, int pageCapacity) throws ServiceException{
        List<Book> books;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            int offset = pageCapacity * pageNumber - pageCapacity;
            books = bookDao.getBooksByPage(offset, pageCapacity);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }
        return books;
    }

    public List<Book> getBooksByGenre(String genre)throws ServiceException{
        List<Book> books;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            books = bookDao.getBooksByGenre(genre);
            transaction.commit();
        }
        catch (PersistException e){
            throw new ServiceException("", e);
        }
        if (books.size()== 0){
            //TODO
        }
        return books;
    }
}
