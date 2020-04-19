package spring.dao.impl;

import spring.dao.WorkPositionDAO;
import spring.dao.common.GenericDAOImpl;
import spring.entity.WorkPosition;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class WorkPositionDAOImpl extends GenericDAOImpl<WorkPosition, Long> implements WorkPositionDAO {

    @Override
    public List<WorkPosition> getVacancy() {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM WorkPosition e WHERE e.worker_id = NULL"
        );
        return query.getResultList();
    }

    @Override
    public List<WorkPosition> getWorkerHistory(Long worker_id) {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM WorkPosition e WHERE e.worker_id.worker_id = :worker_id"
        ).setParameter("worker_id", worker_id);
        return query.getResultList();
    }

    @Override
    public WorkPosition getWorkerCurrent(Long worker_id) {
        try {
            TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                    "SELECT e FROM WorkPosition e WHERE e.worker_id.worker_id = :worker_id AND e.retire_date = NULL"
            ).setParameter("worker_id", worker_id);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<WorkPosition> getCurrentPositions() {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM WorkPosition e WHERE e.retire_date = NULL AND e.worker_id != NULL"
        );
        return query.getResultList();
    }
}