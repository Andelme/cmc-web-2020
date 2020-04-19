package spring.dao;

import spring.dao.common.GenericDAO;
import spring.entity.Department;
import spring.entity.WorkPosition;

import java.util.List;

public interface DepartmentDAO extends GenericDAO<Department, Long> {

    Department getByDepartmentName(String department_name);

    List<Department> getHeadDepartments();

    List<WorkPosition> getDepartmentWorkers(Long department_id);

    List<WorkPosition> getDepartmentVacancy(Long department_id);
}
