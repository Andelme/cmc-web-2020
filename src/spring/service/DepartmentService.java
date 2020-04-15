package spring.service;

import spring.entity.Department;
import spring.service.common.GenericService;

import java.util.List;

public interface DepartmentService extends GenericService<Department, Long> {

    List<Department> getByHeadDepartment(Long head_department);

    Department getByDepartmentName(String department_name);
}
