package dao;

import entity.Worker;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

public class WorkerDAO extends GenericDAO<Worker, Long> {
    @SuppressWarnings("unchecked")
    public List<Worker> getByDepartmentName(String department_name) {
        TypedQuery<Worker> query = getSession().createQuery(
                "SELECT e FROM Worker e INNER JOIN WorkPosition p ON p.worker_id = e " +
                        "WHERE p.department_id.department_name = :department_name"
        ).setParameter("department_name", department_name);
        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<Worker> getByPostypeName(String postype_name) {
        TypedQuery<Worker> query = getSession().createQuery(
                "SELECT e FROM Worker e INNER JOIN WorkPosition p ON p.worker_id = e " +
                        "WHERE p.postype_id.postype_name = :postype_name AND p.retire_date = NULL"
        ).setParameter("postype_name", postype_name);
        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<Worker> getByHireDate(Date startDate, Date endDate) {
        TypedQuery<Worker> query = getSession().createQuery(
                "SELECT e FROM Worker e WHERE e.hire_date BETWEEN :startDate AND :endDate"
        ).setParameter("startDate", startDate, TemporalType.DATE)
         .setParameter("endDate", endDate, TemporalType.DATE);
        return query.getResultList();
    }
}
