package periodic.dao;

import java.io.Serializable;
/**
 * Интерфейс идентефицируемых объектов
 */
public interface Identified<PK extends Serializable> {

    public PK getId();
}
