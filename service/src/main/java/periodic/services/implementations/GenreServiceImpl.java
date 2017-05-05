package periodic.services.implementations;

import periodic.dao.implementations.GenreDaoImpl;

public class GenreServiceImpl {

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
}
