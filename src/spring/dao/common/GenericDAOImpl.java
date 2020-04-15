package spring.dao.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T, K extends Serializable> implements GenericDAO<T, K> {
    private final Class<T> type;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public GenericDAOImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getByID(K id) {
        return getCurrentSession().get(type, id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return getCurrentSession().createQuery(criteria).getResultList();
    }

    @Override
    public void save(T obj) {
        getCurrentSession().save(obj);
    }

    @Override
    public void update(T obj) {
        getCurrentSession().update(obj);
    }

    @Override
    public void delete(T obj) {
        getCurrentSession().delete(obj);
    }
}
