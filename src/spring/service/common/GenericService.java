package spring.service.common;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, K extends Serializable> {

    T getByID(K id);

    List<T> getAll();

    void save(T obj);

    void update(T obj);

    void delete(T obj);
}
