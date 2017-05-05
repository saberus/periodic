package periodic.dao.implementations;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import periodic.dao.AbstractDao;
import periodic.dao.exceptions.PersistException;
import periodic.entities.Book;
import periodic.utils.HibernateUtil;

import java.util.List;

public class BookDaoImpl extends AbstractDao<Book, Long> {

    private static volatile BookDaoImpl instance;

    private BookDaoImpl(){
        super(Book.class);
    }

    public static BookDaoImpl getInstance(){
        if (instance == null){
            synchronized (BookDaoImpl.class){
                if (instance  == null){
                    instance = new BookDaoImpl();
                }
            }
        }
        return instance;
    }

    public List<Book> getBooksByGenre(String genre) throws PersistException{
        try{
            session = HibernateUtil.getInstance().getSession();
/*        return (List<Book>) session.createCriteria(Book.class)
                .add(Restrictions.like("book_genre", genre))
                .list();*/
            criteria = session.createCriteria(Book.class);
            List<Book> books = (List<Book>) criteria.list();
            return books;
        }
        catch (HibernateException e){
            throw new PersistException("", e);
        }


    }

    public List<Book> getBooksByPage(int pageOffset, int pageCapacity) throws PersistException {
        try {
            session = HibernateUtil.getInstance().getSession();
            criteria = session.createCriteria(Book.class);
            criteria.setMaxResults(pageCapacity);
            criteria.setFirstResult(pageOffset);
            criteria.addOrder(Order.desc("createdAT"));
            List<Book> books = (List<Book>) criteria.list();
            return books;
        }
        catch (HibernateException e){
            throw new PersistException("", e);
        }
    }

}
