package periodic.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param<T> тип объекта персистенции
 * @param<PK> тип первич
 */

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**Создает новую запись и соответствующий ей объект*/
    public T create() throws SQLException;

    /**Создает новую запись и соответствующую объекту object*/
    public T persist(T object) throws SQLException;

    /**Возвращает объект соответсвующий записи с первичным ключом или key или null*/
    public T getByPK(int key) throws SQLException;

    /**Сохраняет состояние объекта group в базе данных*/
    public void update(T object) throws SQLException;

    /**Удаляет запись объекта из базы данных*/
    public void delete(T object) throws SQLException;

    /**Возвращает Список объектов соответствующий всем записям в базе данных*/
    public List<T> getAll() throws SQLException;

}
