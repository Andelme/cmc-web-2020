package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@SuppressWarnings("unchecked")
public class GenericDAO<T, K extends Serializable> {
    private final Class<T> type;
    @Autowired
    SessionFactory sessionFactory;

    public GenericDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T getByID(K id) {
        return getCurrentSession().get(type, id);
    }

    @Transactional
    public List<T> getAll() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return getCurrentSession().createQuery(criteria).getResultList();
    }

    public void save(T obj) {
        getCurrentSession().save(obj);
    }

    public void update(T obj) {
        getCurrentSession().update(obj);
    }

    public void delete(T obj) {
        getCurrentSession().delete(obj);
    }
}