package periodic.dao.implementations;

import periodic.dao.AbstractDao;
import periodic.entities.State;

public class StateDaoImpl extends AbstractDao<State, Long> {

    private static volatile StateDaoImpl instance;

    private StateDaoImpl(){
        super(State.class);
    }

    public static StateDaoImpl getInstance(){
        if (instance == null){
            synchronized(StateDaoImpl.class){
                if (instance == null){
                    instance = new StateDaoImpl();
                }
            }
        }
        return instance;
    }
}
