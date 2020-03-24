package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAO<T, K extends Serializable> {
    private Class<T> type;
    Session session;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session session) {
        this.session = session;
    }
    public Session getSession() {
        return session;
    }

    public Class<T> getType() {
        return type;
    }

    public T getByID(K id) {
        return getSession().get(type, id);
    }

    public List<T> getAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return getSession().createQuery(criteria).getResultList();
    }

    public void save(T obj) {
        getSession().save(obj);
    }

    public void update(T obj) {
        getSession().update(obj);
    }

    public void delete(T obj) {
        getSession().delete(obj);
    }
}