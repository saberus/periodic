package periodic.dao;

import periodic.dao.exceptions.PersistException;
import periodic.entities.Identified;

import java.io.Serializable;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param<T> тип объекта персистенции
 * @param<PK> тип первич
 */

public interface IGenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**Создает новую запись соответствующую объекту object*/
    public Long create(T object) throws PersistException;

    /**Возвращает объект соответсвующий записи с первичным ключом или key или null*/
    public T getByPK(int key) throws PersistException;

    /**Сохраняет состояние объекта group в базе данных*/
    public void update(T object) throws PersistException;

    /**Удаляет запись объекта из базы данных*/
    public void delete(int key) throws PersistException;

    /**Возвращает Список объектов соответствующий всем записям в базе данных*/
    public List<T> getAll() throws PersistException;

}
