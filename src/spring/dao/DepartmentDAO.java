package spring.dao;

import spring.dao.common.GenericDAO;
import spring.entity.Department;

import java.util.List;

public interface DepartmentDAO extends GenericDAO<Department, Long> {
    List<Department> getByHeadDepartment(Long head_department);

    Department getByDepartmentName(String department_name);
}
