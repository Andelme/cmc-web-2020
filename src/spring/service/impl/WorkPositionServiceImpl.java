package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.WorkPositionDAO;
import spring.dao.common.GenericDAO;
import spring.entity.WorkPosition;
import spring.service.WorkPositionService;
import spring.service.common.GenericServiceImpl;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class WorkPositionServiceImpl extends GenericServiceImpl<WorkPosition, Long> implements WorkPositionService {

    @Autowired
    private WorkPositionDAO workPositionDAO;

    @Override
    protected GenericDAO<WorkPosition, Long> getDAO() {
        return workPositionDAO;
    }

    @Override
    public List<WorkPosition> getVacancy() {
        return workPositionDAO.getVacancy();
    }

    @Override
    public List<WorkPosition> getWorkerHistory(Long worker_id) {
        return workPositionDAO.getWorkerHistory(worker_id);
    }

    @Override
    public WorkPosition getWorkerCurrent(Long worker_id) {
        return workPositionDAO.getWorkerCurrent(worker_id);
    }

    @Override
    public List<WorkPosition> getCurrentPositions() {
        return workPositionDAO.getCurrentPositions();
    }

    @Override
    public void unbindWorker(Long worker_id) {
        WorkPosition prevWorkPosition = getWorkerCurrent(worker_id);
        if (prevWorkPosition != null) {
            prevWorkPosition.setRetire_date(new Date(System.currentTimeMillis()));
            WorkPosition newVacancy = new WorkPosition();
            newVacancy.setPostype_id(prevWorkPosition.getPostype_id());
            newVacancy.setDepartment_id(prevWorkPosition.getDepartment_id());
            newVacancy.setWork_rate(prevWorkPosition.getWork_rate());
            update(prevWorkPosition);
            save(newVacancy);
        }
    }
}
