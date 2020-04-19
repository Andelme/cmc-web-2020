package spring.service;


import spring.entity.WorkPosition;
import spring.service.common.GenericService;

import java.util.List;

public interface WorkPositionService extends GenericService<WorkPosition, Long> {

    List<WorkPosition> getVacancy();

    List<WorkPosition> getWorkerHistory(Long worker_id);

    WorkPosition getWorkerCurrent(Long worker_id);

    void unbindWorker(Long worker_id);

    List<WorkPosition> getCurrentPositions();
}
