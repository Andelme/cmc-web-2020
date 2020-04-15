package spring.dao;

import spring.dao.common.GenericDAO;
import spring.entity.WorkPosition;

import java.util.List;

public interface WorkPositionDAO extends GenericDAO<WorkPosition, Long> {

    List<WorkPosition> getVacancy();

    List<WorkPosition> getByWorkerId(Long worker_id);
}
