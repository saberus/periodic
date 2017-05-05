package periodic.dao.implementations;

import periodic.dao.AbstractDao;
import periodic.entities.Genre;

import java.util.List;

public class GenreDaoImpl extends AbstractDao<Genre,Long> {

    private static volatile GenreDaoImpl instance;

    private GenreDaoImpl(){
        super(Genre.class);
    }

    public static GenreDaoImpl getInstance(){
        if (instance == null){
            synchronized (GenreDaoImpl.class){
                if (instance == null){
                    instance = new GenreDaoImpl();
                }
            }
        }
        return instance;
    }

}
