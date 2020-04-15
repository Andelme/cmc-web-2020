package spring.service.common;

import org.springframework.transaction.annotation.Transactional;
import spring.dao.common.GenericDAO;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class GenericServiceImpl<T, K extends Serializable> implements GenericService<T, K> {

    protected abstract GenericDAO<T, K> getDAO();

    @Override
    public T getByID(K id) {
        return getDAO().getByID(id);
    }

    @Override
    public List<T> getAll() {
        return getDAO().getAll();
    }

    @Override
    public void save(T obj) {
        getDAO().save(obj);
    }

    @Override
    public void update(T obj) {
        getDAO().update(obj);
    }

    @Override
    public void delete(T obj) {
        getDAO().delete(obj);
    }
}