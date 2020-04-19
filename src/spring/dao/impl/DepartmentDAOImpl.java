package spring.dao.impl;

import spring.dao.DepartmentDAO;
import spring.dao.common.GenericDAOImpl;
import spring.entity.Department;
import org.springframework.stereotype.Repository;
import spring.entity.WorkPosition;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class DepartmentDAOImpl extends GenericDAOImpl<Department, Long> implements DepartmentDAO {

    @Override
    public Department getByDepartmentName(String department_name) {
        try {
            TypedQuery<Department> query = getCurrentSession().createQuery(
                    "SELECT e FROM Department e WHERE e.department_name = :department_name"
            ).setParameter("department_name", department_name);
            return query.getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Department> getHeadDepartments() {
        TypedQuery<Department> query = getCurrentSession().createQuery(
                "SELECT e FROM Department e WHERE e.head_department = NULL"
        );
        return query.getResultList();
    }

    @Override
    public List<WorkPosition> getDepartmentWorkers(Long department_id) {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM WorkPosition e WHERE e.worker_id != NULL AND " +
                        "e.retire_date = NULL AND " +
                        "e.department_id.department_id = :department_id"
        ).setParameter("department_id", department_id);
        return query.getResultList();
    }

    @Override
    public List<WorkPosition> getDepartmentVacancy(Long department_id) {
        TypedQuery<WorkPosition> query = getCurrentSession().createQuery(
                "SELECT e FROM WorkPosition e WHERE e.worker_id = NULL AND " +
                        "e.department_id.department_id = :department_id"
        ).setParameter("department_id", department_id);
        return query.getResultList();
    }
}