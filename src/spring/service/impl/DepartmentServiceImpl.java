package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.DepartmentDAO;
import spring.dao.common.GenericDAO;
import spring.entity.Department;
import spring.service.DepartmentService;
import spring.service.common.GenericServiceImpl;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<Department, Long> implements DepartmentService {

    private DepartmentDAO departmentDAO;

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    protected GenericDAO<Department, Long> getDAO() {
        return departmentDAO;
    }

    @Override
    public List<Department> getByHeadDepartment(Long head_department) {
        return departmentDAO.getByHeadDepartment(head_department);
    }

    @Override
    public Department getByDepartmentName(String department_name) {
        return departmentDAO.getByDepartmentName(department_name);
    }
}
