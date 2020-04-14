package dao;

import entity.Worker;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class WorkerDAO extends GenericDAO<Worker, Long> {
    public List<Worker> getByDepartmentName(String department_name) {
        TypedQuery<Worker> query = getCurrentSession().createQuery(
                "SELECT e FROM Worker e INNER JOIN WorkPosition p ON p.worker_id = e " +
                        "WHERE p.department_id.department_name = :department_name"
        ).setParameter("department_name", department_name);
        return query.getResultList();
    }
    public List<Worker> getByPostypeName(String postype_name) {
        TypedQuery<Worker> query = getCurrentSession().createQuery(
                "SELECT e FROM Worker e INNER JOIN WorkPosition p ON p.worker_id = e " +
                        "WHERE p.postype_id.postype_name = :postype_name AND p.retire_date = NULL"
        ).setParameter("postype_name", postype_name);
        return query.getResultList();
    }
    public List<Worker> getByHireDate(Timestamp startDate, Timestamp endDate) {
        TypedQuery<Worker> query = getCurrentSession().createQuery(
                "SELECT e FROM Worker e WHERE e.hire_date BETWEEN :startDate AND :endDate"
        ).setParameter("startDate", startDate, TemporalType.TIMESTAMP)
         .setParameter("endDate", endDate, TemporalType.TIMESTAMP);
        return query.getResultList();
    }
}
