package periodic.dao.implementations;

import periodic.dao.AbstractDao;
import periodic.entities.Book;

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

}
