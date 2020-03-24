package dao;

import entity.WorkPosition;

import javax.persistence.TypedQuery;
import java.util.List;

public class WorkPositionDAO extends GenericDAO<WorkPosition, Long> {
    @SuppressWarnings("unchecked")
    public List<WorkPosition> getVacancy() {
        TypedQuery<WorkPosition> query = getSession().createQuery(
                "SELECT e FROM WorkPosition e WHERE e.worker_id = NULL"
        );
        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<WorkPosition> getByWorkerId(Long worker_id) {
        TypedQuery<WorkPosition> query = getSession().createQuery(
                "SELECT e FROM Worker w, WorkPosition e WHERE w.worker_id = :worker_id AND e.worker_id = w"
        ).setParameter("worker_id", worker_id);
        return query.getResultList();
    }
}