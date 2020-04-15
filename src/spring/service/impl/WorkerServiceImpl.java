package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.WorkerDAO;
import spring.dao.common.GenericDAO;
import spring.entity.Worker;
import spring.service.WorkerService;
import spring.service.common.GenericServiceImpl;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class WorkerServiceImpl extends GenericServiceImpl<Worker, Long> implements WorkerService {

    private WorkerDAO workerDAO;

    @Autowired
    public void setWorkerDAO(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    @Override
    protected GenericDAO<Worker, Long> getDAO() {
        return workerDAO;
    }

    @Override
    public List<Worker> getByDepartmentName(String department_name) {
        return workerDAO.getByDepartmentName(department_name);
    }

    @Override
    public List<Worker> getByPostypeName(String postype_name) {
        return workerDAO.getByPostypeName(postype_name);
    }

    @Override
    public List<Worker> getByHireDate(Timestamp startDate, Timestamp endDate) {
        return workerDAO.getByHireDate(startDate, endDate);
    }
}
