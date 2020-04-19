package spring.service;

import spring.entity.Department;
import spring.entity.WorkPosition;
import spring.service.common.GenericService;

import java.util.List;

public interface DepartmentService extends GenericService<Department, Long> {

    List<Department> getHeadDepartments();

    List<WorkPosition> getDepartmentWorkers(Long department_id);

    List<WorkPosition> getDepartmentVacancy(Long department_id);
}
