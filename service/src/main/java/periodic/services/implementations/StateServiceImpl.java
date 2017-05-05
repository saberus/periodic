package periodic.services.implementations;

import periodic.dao.implementations.StateDaoImpl;

public class StateServiceImpl {

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
}
