package dao;

import entity.WorkPosition;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class WorkPositionDAO extends GenericDAO<WorkPosition, Long> {
    public List<WorkPosition> getVacancy() {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM WorkPosition e WHERE e.worker_id = NULL"
        );
        return query.getResultList();
    }
    public List<WorkPosition> getByWorkerId(Long worker_id) {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM Worker w, WorkPosition e WHERE w.worker_id = :worker_id AND e.worker_id = w"
        ).setParameter("worker_id", worker_id);
        return query.getResultList();
    }
}