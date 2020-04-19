package spring.dao.impl;

import spring.dao.WorkerDAO;
import spring.dao.common.GenericDAOImpl;
import spring.entity.Worker;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class WorkerDAOImpl extends GenericDAOImpl<Worker, Long> implements WorkerDAO {

    @Override
    public List<Worker> getSpareWorkers() {
        TypedQuery<Worker> query = getCurrentSession().createQuery(
                "SELECT e FROM Worker e WHERE e NOT IN (SELECT p.worker_id FROM WorkPosition p WHERE p.retire_date = NULL)"
        );
        return query.getResultList();
    }
}