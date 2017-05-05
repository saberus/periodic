package periodic.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import periodic.dao.implementations.BookDaoImpl;
import periodic.entities.Identified;
import periodic.utils.HibernateUtil;

import java.io.Serializable;

public abstract class AbstractService<T extends Identified<PK>, PK extends Long> implements IGenericService<T,PK> {

        protected HibernateUtil util = HibernateUtil.getInstance();
        protected Session session;
        protected Transaction transaction;

}
