package spring.dao.common;

import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, K extends Serializable> {

    void setSessionFactory(SessionFactory sessionFactory);

    T getByID(K id);

    List<T> getAll();

    void save(T obj);

    void update(T obj);

    void delete(T obj);
}