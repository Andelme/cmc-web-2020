package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.WorkPositionDAO;
import spring.dao.common.GenericDAO;
import spring.entity.WorkPosition;
import spring.service.WorkPositionService;
import spring.service.common.GenericServiceImpl;

import java.util.List;

@Service
@Transactional
public class WorkPositionServiceImpl extends GenericServiceImpl<WorkPosition, Long> implements WorkPositionService {

    private WorkPositionDAO workPositionDAO;

    @Autowired
    public void setWorkPositionDAO(WorkPositionDAO workPositionDAO) {
        this.workPositionDAO = workPositionDAO;
    }

    @Override
    protected GenericDAO<WorkPosition, Long> getDAO() {
        return workPositionDAO;
    }

    @Override
    public List<WorkPosition> getVacancy() {
        return workPositionDAO.getVacancy();
    }

    @Override
    public List<WorkPosition> getByWorkerId(Long worker_id) {
        return workPositionDAO.getByWorkerId(worker_id);
    }
}
