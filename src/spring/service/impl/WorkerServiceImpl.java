package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.WorkerDAO;
import spring.dao.common.GenericDAO;
import spring.entity.Worker;
import spring.service.WorkerService;
import spring.service.common.GenericServiceImpl;

import java.util.List;

@Service
@Transactional
public class WorkerServiceImpl extends GenericServiceImpl<Worker, Long> implements WorkerService {

    @Autowired
    private WorkerDAO workerDAO;

    @Override
    protected GenericDAO<Worker, Long> getDAO() {
        return workerDAO;
    }

    @Override
    public List<Worker> getSpareWorkers() {
        return workerDAO.getSpareWorkers();
    }
}
