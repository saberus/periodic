package periodic.services;

import periodic.entities.Identified;
import periodic.exceptions.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T extends Identified<PK>, PK extends Serializable> {

    /**Создает новую запись соответствующую объекту object*/
    public Long create(T object) throws ServiceException;

    /**Возвращает объект соответсвующий записи с первичным ключом или key или null*/
    public T getByPK(int key) throws ServiceException;

    /**Сохраняет состояние объекта group в базе данных*/
    public void update(T object) throws ServiceException;

    /**Удаляет запись объекта из базы данных*/
    public void delete(int key) throws ServiceException;

    /**Возвращает Список объектов соответствующий всем записям в базе данных*/
    public List<T> getAll() throws ServiceException;
}
