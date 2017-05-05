package periodic.services.implementations;

import periodic.dao.implementations.BookDaoImpl;

public class BookServiceImpl {

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
}
