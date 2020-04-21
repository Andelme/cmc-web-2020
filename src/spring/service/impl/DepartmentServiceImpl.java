package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.DepartmentDAO;
import spring.dao.common.GenericDAO;
import spring.entity.Department;
import spring.entity.WorkPosition;
import spring.service.DepartmentService;
import spring.service.common.GenericServiceImpl;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<Department, Long> implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    protected GenericDAO<Department, Long> getDAO() {
        return departmentDAO;
    }

    @Override
    public Department getByDepartmentName(String department_name) {
        return departmentDAO.getByDepartmentName(department_name);
    }

    @Override
    public List<Department> getHeadDepartments() {
        return departmentDAO.getHeadDepartments();
    }

    @Override
    public List<WorkPosition> getDepartmentWorkers(Long department_id) {
        return departmentDAO.getDepartmentWorkers(department_id);
    }

    @Override
    public List<WorkPosition> getDepartmentVacancy(Long department_id) {
        return departmentDAO.getDepartmentVacancy(department_id);
    }
}
