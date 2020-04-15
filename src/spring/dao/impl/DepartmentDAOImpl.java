package spring.dao.impl;

import spring.dao.DepartmentDAO;
import spring.dao.common.GenericDAOImpl;
import spring.entity.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class DepartmentDAOImpl extends GenericDAOImpl<Department, Long> implements DepartmentDAO {

    @Override
    public List<Department> getByHeadDepartment(Long head_department) {
        TypedQuery<Department> query = getCurrentSession().createQuery(
                "SELECT e FROM Department e WHERE e.head_department.department_id = :head_department"
        ).setParameter("head_department", head_department);
        return query.getResultList();
    }

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
}