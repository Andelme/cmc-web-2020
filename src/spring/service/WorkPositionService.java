package spring.service;


import spring.entity.WorkPosition;
import spring.service.common.GenericService;

import java.util.List;

public interface WorkPositionService extends GenericService<WorkPosition, Long> {

    List<WorkPosition> getVacancy();

    List<WorkPosition> getByWorkerId(Long worker_id);
}
