package dao;

import entity.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class DepartmentDAO extends GenericDAO<Department, Long> {
    public List<Department> getByHeadDepartment(Long head_department) {
        TypedQuery<Department> query = getCurrentSession().createQuery(
                "SELECT e FROM Department e WHERE e.head_department.department_id = :head_department"
        ).setParameter("head_department", head_department);
        return query.getResultList();
    }
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
}