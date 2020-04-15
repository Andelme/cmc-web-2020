package spring.dao.impl;

import spring.dao.WorkPositionDAO;
import spring.dao.common.GenericDAOImpl;
import spring.entity.WorkPosition;
import org.springframework.stereotype.Repository;

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
    public List<WorkPosition> getByWorkerId(Long worker_id) {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM Worker w, WorkPosition e WHERE w.worker_id = :worker_id AND e.worker_id = w"
        ).setParameter("worker_id", worker_id);
        return query.getResultList();
    }
}